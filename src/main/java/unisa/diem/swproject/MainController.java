package unisa.diem.swproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import unisa.diem.swproject.model.*;
import unisa.diem.swproject.model.tools.RectangleTool;

import java.util.Map;

public class MainController {

    private final Project project;

    private final Map<String, Tool> toolMap;

    public MainController() {
        project = new Project("Untitled");
        Sheet sheet = new Sheet("Sheet 1", SheetFormat.A4, null);
        project.addSheet(sheet);

        toolMap = Map.ofEntries(
            Map.entry("rectangle", new RectangleTool(sheet.shapeManager()))
        );
    }

    @FXML
    private ScrollPane canvasContainer;

    @FXML
    public void initialize() {
        Sheet sheet = project.getSheet();
        canvasContainer.setContent(sheet);
        sheet.buildDrawingArea();

        sheet.setOnMousePressed(e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseDown(e.getX(), e.getY());
            }
        });

        sheet.setOnMouseReleased(e -> {
            if (sheet.getCurrentTool() != null) {
                sheet.getCurrentTool().mouseUp(e.getX(), e.getY());
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
}