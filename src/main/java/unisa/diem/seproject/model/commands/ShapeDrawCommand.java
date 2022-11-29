package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeDrawCommand implements Command {
    private final Shape shape;
    private final ShapeManager sm;

    public ShapeDrawCommand(Shape s, ShapeManager sm) {
        this.shape = s;
        this.sm = sm;
    }

    @Override
    public void execute() {
        sm.add(shape);
    }
}
