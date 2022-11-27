package unisa.diem.swproject.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final List<Sheet> sheets;
    private final CommandManager commandManager;

    public Project() {
        this.sheets = new ArrayList<>();
        this.commandManager = new CommandManager();
    }

    public CommandManager commandManager() {
        return commandManager;
    }

    public void addSheet(Sheet sheet) {
        sheets.add(sheet);
    }

    public Sheet getSheet() {
        return sheets.get(0);
    }
}
