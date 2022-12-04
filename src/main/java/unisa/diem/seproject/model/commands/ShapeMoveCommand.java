package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeMoveCommand implements Command {

    private final Shape shape;
    private final ShapeManager sm;
    private final double deltaX;
    private final double deltaY;

    public ShapeMoveCommand(ShapeManager sm, Shape shape, double deltaX, double deltaY) {
        this.sm = sm;
        this.shape = shape;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public void execute() {
        sm.move(shape, deltaX, deltaY);
    }

    @Override
    public void rollback() {
        sm.move(shape, -deltaX, -deltaY);
        sm.redraw();
    }
}
