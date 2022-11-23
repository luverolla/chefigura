package unisa.diem.swproject;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import unisa.diem.swproject.model.CommandManager;
import unisa.diem.swproject.model.Project;
import unisa.diem.swproject.model.Sheet;
import unisa.diem.swproject.model.SheetFormat;

public class MainController {

    private Project project;

    private final CommandManager cm;

    public MainController() {
        cm = new CommandManager();
        project = new Project("Untitled");
        Sheet sheet = new Sheet("Sheet 1", SheetFormat.A4, null);
        project.addSheet(sheet);
    }

    @FXML
    private ScrollPane canvasContainer;

    @FXML
    public void initialize() {
        Sheet sheet = project.getSheet();
        canvasContainer.setContent(sheet);
        sheet.buildDrawingArea();
    }
}