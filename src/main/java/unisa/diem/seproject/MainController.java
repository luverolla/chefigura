package unisa.diem.seproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.tools.*;

import java.io.File;
import java.util.Map;

public class MainController {

    private Project project;
    private Map<String, Tool> toolMap;
    @FXML
    public ColorPicker strokeColorPicker;
    @FXML
    public ColorPicker fillColorPicker;
    @FXML
    public MenuItem fileChooserRef;
    @FXML
    private ScrollPane canvasContainer;

    @FXML
    public MenuItem menuOptionCopy;
    @FXML
    public MenuItem menuOptionCut;
    @FXML
    public MenuItem menuOptionPaste;

    private final ContextMenu contextMenu;
    private final CommandManager commandManager;

    public MainController() {
        commandManager = new CommandManager();
        project = new Project(commandManager);
        Sheet sheet = new Sheet(SheetFormat.NONE, commandManager);
        project.addSheet(sheet);
        contextMenu = new ContextMenu();
    }

    @FXML
    public void initialize() {
        _init(project.getSheet());

        menuOptionCopy.disableProperty().bind(project.getSheet().shapeManager().selectedShapeProperty.isNull());
        menuOptionCut.disableProperty().bind(project.getSheet().shapeManager().selectedShapeProperty.isNull());
        menuOptionPaste.disableProperty().bind(project.getSheet().shapeManager().copiedShapeProperty.isNull());
    }

    private void _init(Sheet sheet) {
        Canvas canvas = new Canvas();
        sheet.buildDrawingArea(canvas);
        canvasContainer.setContent(canvas);
        sheet.shapeManager().redraw();
        toolMap = Map.ofEntries(
                Map.entry("rectangle", new RectangleTool(sheet.shapeManager())),
                Map.entry("ellipse", new EllipseTool(sheet.shapeManager())),
                Map.entry("segment", new LineSegmentTool(sheet.shapeManager()))
        );

        canvas.setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                double x = event.getX(),
                        y = event.getY();

                if(project.getSheet().shapeManager().selectedShapeProperty.get() != null) {
                    Shape shape = project.getSheet().shapeManager().selectedShapeProperty.get();
                    MenuItem paste = new MenuItem("Paste");
                    paste.setOnAction(event1 -> commandManager.execute(new ShapePasteCommand(project.getSheet().shapeManager(), shape)));
                    contextMenu.getItems().add(paste);
                    if (shape.contains(x, y)) {
                        MenuItem cut = new MenuItem("Cut");
                        MenuItem copy = new MenuItem("Copy");
                        cut.setOnAction(event1 -> commandManager.execute(new ShapeCutCommand(project.getSheet().shapeManager(), shape)));
                        copy.setOnAction(event1 -> commandManager.execute(project.getSheet().shapeManager().copyShape(shape)));
                        contextMenu.getItems().addAll(cut, copy);
                        contextMenu.show((Node) event.getSource(), event.getScreenX(), event.getScreenY());
                    }
                }
            }
        });

        strokeColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            for(Tool t: toolMap.values()) {
                if(t instanceof ShapeTool) {
                    ((ShapeTool) t).setStrokeColor(new Color(newValue));
                }
            }
        });
        fillColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            for(Tool t: toolMap.values()) {
                if(t instanceof ClosedShapeTool) {
                    ((ClosedShapeTool) t).setFillColor(new Color(newValue));
                }
            }
        });
        canvas.setOnMousePressed(e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseDown(e.getX(), e.getY());
            }
        });
        canvas.setOnMouseMoved(e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseDrag(e.getX(), e.getY());
            }
        });
    }

    @FXML
    public void selectTool(ActionEvent e) {
        Node node = (Node) e.getSource();
        String toolName = (String) node.getUserData();
        Tool chosen = toolMap.get(toolName);
        if(chosen.equals(project.getSheet().getCurrentTool())) {
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
            _init(project.getSheet());
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
        project.getCommandManager().rollback();
    }
}