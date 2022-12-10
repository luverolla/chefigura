package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeResizeCommand implements Command {

    private final ShapeManager sm;
    private final Shape shape;
    private final double resizeFactor;

    public ShapeResizeCommand(ShapeManager sm, Shape shape, double resizeFactor) {
        this.sm = sm;
        this.shape = shape;
        this.resizeFactor = resizeFactor;
    }

    @Override
    public void execute() {
        sm.resize(shape, resizeFactor);
    }

    @Override
    public void rollback() {
        sm.resize(shape, 1 / resizeFactor);
        sm.redraw();
    }
}

