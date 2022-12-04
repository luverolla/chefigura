package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeResizeCommand implements Command {

    private final ShapeManager sm;
    private final Shape shape;
    private final double delta;

    public ShapeResizeCommand(ShapeManager sm, Shape shape, double delta) {
        this.sm = sm;
        this.shape = shape;
        this.delta = delta;
    }

    @Override
    public void execute() {
        sm.resize(shape, delta);
    }

    @Override
    public void rollback() {
        sm.resize(shape, -delta);
        sm.redraw();
    }
}

