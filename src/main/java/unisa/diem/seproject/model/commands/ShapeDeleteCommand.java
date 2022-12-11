package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeDeleteCommand implements Command {
    private final Shape shape;
    private final ShapeManager sm;

    public ShapeDeleteCommand(ShapeManager sm, Shape s) {
        this.shape = s;
        this.sm = sm;
    }

    @Override
    public void execute() {
        sm.remove(shape);
    }

    @Override
    public void rollback() {
        sm.add(shape);
    }
}
