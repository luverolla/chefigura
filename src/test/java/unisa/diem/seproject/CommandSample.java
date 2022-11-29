package unisa.diem.seproject;

import unisa.diem.seproject.model.Command;

public class CommandSample implements Command {
    public static int count = 0;

    public void execute() {
        count++;
    }
}
