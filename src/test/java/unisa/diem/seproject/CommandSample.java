package unisa.diem.seproject;

import unisa.diem.seproject.model.Command;

public class CommandSample implements Command {
    public static int count = 0;

    @Override
    public void execute() {
        count++;
    }

    @Override
    public void rollback() {
        count--;
    }
}
