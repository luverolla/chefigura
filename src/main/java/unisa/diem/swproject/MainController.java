package unisa.diem.swproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import unisa.diem.swproject.model.*;
import unisa.diem.swproject.model.tools.*;

import java.util.Map;

public class MainController {

    private final Project project;
    private final Map<String, Tool> toolMap;
    @FXML
    public ColorPicker strokeColorPicker;
    @FXML
    public ColorPicker fillColorPicker;

    public MainController() {
        project = new Project("Untitled");
        Sheet sheet = new Sheet("Sheet 1", SheetFormat.NONE, project.commandManager());
        project.addSheet(sheet);

        toolMap = Map.ofEntries(
            Map.entry("rectangle", new RectangleTool(sheet.shapeManager())),
            Map.entry("ellipse", new EllipseTool(sheet.shapeManager())),
            Map.entry("segment", new LineSegmentTool(sheet.shapeManager()))
        );
    }

    @FXML
    private ScrollPane canvasContainer;

    @FXML
    public void initialize() {
        Sheet sheet = project.getSheet();
        canvasContainer.setContent(sheet);
        sheet.buildDrawingArea();
        strokeColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            for(Tool t: toolMap.values()) {
                if(t instanceof ShapeTool) {
                    ((ShapeTool) t).setStrokeColor(newValue);
                }
            }
        });
        fillColorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            for(Tool t: toolMap.values()) {
                if(t instanceof ClosedShapeTool) {
                    ((ClosedShapeTool) t).setFillColor(newValue);
                }
            }
        });

        sheet.setOnMousePressed(e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseDown(e.getX(), e.getY());
            }
        });

        sheet.setOnMouseMoved(e -> {
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
}
