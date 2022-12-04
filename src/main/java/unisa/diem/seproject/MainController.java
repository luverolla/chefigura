package unisa.diem.seproject;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeCutCommand;
import unisa.diem.seproject.model.commands.ShapeDeleteCommand;
import unisa.diem.seproject.model.commands.ShapePasteCommand;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.tools.*;

import java.io.File;
import java.util.List;
import java.util.Map;

public class MainController {

    private Project project;
    private Map<String, Tool> toolMap;
    @FXML
    private ColorPicker strokeColorPicker;
    @FXML
    private ColorPicker fillColorPicker;
    @FXML
    private MenuItem fileChooserRef;
    @FXML
    private ScrollPane canvasContainer;
    @FXML
    private MenuItem menuOptionCopy;
    @FXML
    private MenuItem menuOptionCut;
    @FXML
    private MenuItem menuOptionPaste;
    @FXML
    private MenuItem menuOptionDelete;
    private final ContextMenu shapeContextMenu;
    private final CommandManager commandManager;

    public MainController() {
        commandManager = new CommandManager();
        project = new Project(commandManager);
        Sheet sheet = new Sheet(SheetFormat.NONE, commandManager);
        project.addSheet(sheet);
        shapeContextMenu = new ContextMenu();
    }

    @FXML
    public void initialize() {
        ObjectProperty<Shape> selShape = project.getSheet().shapeManager().selectedShapeProperty;
        initSheet(project.getSheet());

        menuOptionCopy.disableProperty().bind(project.getSheet().shapeManager().selectedShapeProperty.isNull());
        menuOptionCut.disableProperty().bind(project.getSheet().shapeManager().selectedShapeProperty.isNull());
        menuOptionPaste.disableProperty().bind(project.getSheet().shapeManager().copiedShapeProperty.isNull());
        menuOptionDelete.disableProperty().bind(project.getSheet().shapeManager().selectedShapeProperty.isNull());

        strokeColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            for(Tool t: toolMap.values()) {
                if(t instanceof ShapeTool) {
                    ((ShapeTool) t).setStrokeColor(new Color(newValue));
                }
            }
            if (selShape.get() != null) {
                project.getSheet().shapeManager().changeShapeColor(selShape.get(), new Color(newValue), new Color(fillColorPicker.getValue()));
            }
        });
        fillColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            for(Tool t: toolMap.values()) {
                if(t instanceof ClosedShapeTool) {
                    ((ClosedShapeTool) t).setFillColor(new Color(newValue));
                }
            }
            if (project.getSheet().shapeManager().selectedShapeProperty.get() != null) {
                if (project.getSheet().shapeManager().selectedShapeProperty.get() instanceof ClosedShape) {
                    project.getSheet().shapeManager().changeShapeColor(selShape.get(), new Color(strokeColorPicker.getValue()), new Color(newValue));
                }
            }
        });
    }

    private void initContextMenu() {
        MenuItem copy = new MenuItem("Copy");
        copy.setOnAction(event -> onCopy());

        MenuItem cut = new MenuItem("Cut");
        cut.setOnAction(event -> onCut());

        MenuItem paste = new MenuItem("Paste");
        paste.setOnAction(event -> onPaste());

        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> onDelete());

        shapeContextMenu.getItems().addAll(copy, cut, paste, delete);
        shapeContextMenu.setAutoHide(true);
    }

    private void initSheet(Sheet sheet) {
        Canvas canvas = new Canvas();
        sheet.buildDrawingArea(canvas);
        canvasContainer.setContent(canvas);
        sheet.shapeManager().redraw();
        initContextMenu();

        toolMap = Map.ofEntries(
                Map.entry("rectangle", new RectangleTool(sheet.shapeManager())),
                Map.entry("ellipse", new EllipseTool(sheet.shapeManager())),
                Map.entry("segment", new LineSegmentTool(sheet.shapeManager())),
                Map.entry("selection", new SelectionTool(sheet.shapeManager(), canvas))
        );

        canvas.setOnContextMenuRequested(event -> {
            double x = event.getX(),
                    y = event.getY();
            if(project.getSheet().shapeManager().selectedShapeProperty.get() != null) {
                Shape shape = project.getSheet().shapeManager().selectedShapeProperty.get();
                if(shape.contains(x, y)) {
                    shapeContextMenu.show(canvasContainer, event.getScreenX(), event.getScreenY());
                }
            }
        });
        canvas.setOnMouseClicked(e -> {
            shapeContextMenu.hide();
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseDown(e.getX(), e.getY());
            }
        });
        canvas.setOnMouseMoved(e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseMove(e.getX(), e.getY());
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseUp(e.getX(), e.getY());
            }
        });
    }

    @FXML
    public void selectTool(ActionEvent e) {
        project.getSheet().shapeManager().selectedShapeProperty.set(null);
        Node node = (Node) e.getSource();
        String toolName = (String) node.getUserData();
        Tool chosen = toolMap.get(toolName);
        if(chosen.equals(project.getSheet().getCurrentTool())) {
            project.getSheet().getCurrentTool().reset();
            project.getSheet().setCurrentTool(null);
        } else {
            project.getSheet().setCurrentTool(chosen);
        }
    }

    @FXML
    public void onSheetClear() {
        Sheet sheet = project.getSheet();
        sheet.shapeManager().clear();
    }

    @FXML
    public void handleLoad() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load project");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Project files", "*.proj"));
        File file = fc.showOpenDialog(fileChooserRef.getParentPopup().getScene().getWindow());
        if (file != null) {
            project = Project.load(file);
            assert project != null;
            initSheet(project.getSheet());
        }
    }

    @FXML
    public void handleSave() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save project");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Project files", "*.proj"));
        File file = project.getFile() != null ? project.getFile() : fc.showSaveDialog(fileChooserRef.getParentPopup().getScene().getWindow());
        if (file != null) {
            Project.save(project, file);
        }
    }

    public void onUndo() {
        project.getCommandManager().undo();
    }

    public void onCopy() {
        project.getSheet().shapeManager().copyShape(project.getSheet().shapeManager().selectedShapeProperty.get());
    }

    public void onCut() {
        commandManager.execute(new ShapeCutCommand(project.getSheet().shapeManager(), project.getSheet().shapeManager().selectedShapeProperty.get()));
    }

    public void onPaste() {
        commandManager.execute(new ShapePasteCommand(project.getSheet().shapeManager(), project.getSheet().shapeManager().copiedShapeProperty.get()));
    }

    public void onDelete() {
        commandManager.execute(new ShapeDeleteCommand(project.getSheet().shapeManager(), project.getSheet().shapeManager().selectedShapeProperty.get()));
    }

    public void resetTool(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ESCAPE) {
            if (project.getSheet().getCurrentTool() != null) {
                project.getSheet().getCurrentTool().reset();
            }
        }
    }
}