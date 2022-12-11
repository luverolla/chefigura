package unisa.diem.seproject.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a drawing project, composed of one or more sheets
 */
public class Project implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Sheet> sheets;
    private transient CommandManager commandManager;
    private transient File file;

    public Project(CommandManager cm) {
        this.sheets = new ArrayList<>();
        this.commandManager = cm;
        this.file = null;
    }

    /**
     * When loading a project from file, the command manager is not serialized, so it must be reinitialized manually
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.commandManager = new CommandManager();
        this.file = null;
    }

    public void addSheet(Sheet sheet) {
        sheets.add(sheet);
    }

    public Sheet getSheet() {
        return sheets.get(0);
    }

    public File getFile() {
        return file;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static void save(Project project, File file) {
        try {
            new ObjectOutputStream(new FileOutputStream(file)).writeObject(project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Project load(File file) {
        try {
            Project proj = (Project) new ObjectInputStream(new FileInputStream(file)).readObject();
            proj.file = file;
            proj.commandManager = new CommandManager();
            proj.sheets.replaceAll(sheet -> new Sheet(sheet, proj.commandManager));
            return proj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
