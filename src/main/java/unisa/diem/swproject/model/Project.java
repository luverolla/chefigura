package unisa.diem.swproject.model;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Sheet> sheets;
    private CommandManager commandManager;

    public Project(String name) {
        this.name = name;
        this.sheets = new ArrayList<>();
        this.commandManager = new CommandManager();
    }
    public Project load(String fileName) {
        Project p = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            p = (Project) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return p;
    }

    public void save(String filePath, Project p) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
