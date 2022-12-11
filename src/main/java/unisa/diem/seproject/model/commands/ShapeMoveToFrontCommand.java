package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeMoveToFrontCommand implements Command {
    private final ShapeManager sm;
    private final Shape shape;
    private int oldZIndex;

    public ShapeMoveToFrontCommand(ShapeManager sm, Shape shape) {
        this.sm = sm;
        this.shape = shape;
    }

    @Override
    public void execute() {
        oldZIndex = shape.getZIndex();
        sm.moveToFront(shape);
    }

    @Override
    public void rollback() {
        sm.moveToBack(shape);
        shape.setZIndex(oldZIndex);
        sm.redraw();
    }
}