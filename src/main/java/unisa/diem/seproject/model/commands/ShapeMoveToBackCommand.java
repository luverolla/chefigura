package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeMoveToBackCommand implements Command {
    private final ShapeManager sm;
    private final Shape shape;
    private int oldZIndex;

    public ShapeMoveToBackCommand(ShapeManager sm, Shape shape) {
        this.sm = sm;
        this.shape = shape;
    }

    @Override
    public void execute() {
        oldZIndex = shape.getZIndex();
        sm.moveToBack(shape);
    }

    @Override
    public void rollback() {
        sm.moveToFront(shape);
        shape.setZIndex(oldZIndex);
        sm.redraw();
    }
}
