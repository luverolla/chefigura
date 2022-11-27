package unisa.diem.swproject;

import unisa.diem.swproject.model.Command;

public class CommandSample implements Command {
    public static int count = 0;

    public void execute() {
        count++;
    }
}
