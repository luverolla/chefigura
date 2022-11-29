package unisa.diem.seproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
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

    public MainController() {
        CommandManager commandManager = new CommandManager();
        project = new Project(commandManager);
        Sheet sheet = new Sheet(SheetFormat.NONE, commandManager);
        project.addSheet(sheet);
    }

    @FXML
    private ScrollPane canvasContainer;

    @FXML
    public void initialize() {
        _init(project.getSheet());
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
}