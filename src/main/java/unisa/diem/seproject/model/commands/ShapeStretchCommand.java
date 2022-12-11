package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeStretchCommand implements Command {
    private final ShapeManager sm;
    private final Shape shape;
    private final double deltaX;
    private final double deltaY;
    private final int direction;

    public ShapeStretchCommand(ShapeManager sm, Shape shape, double deltaX, double deltaY, int direction) {
        this.sm = sm;
        this.shape = shape;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.direction = direction;
    }

    @Override
    public void execute() {
        sm.stretch(shape, deltaX, deltaY, direction);
    }

    @Override
    public void rollback() {
        sm.stretch(shape, -deltaX, -deltaY, direction);
    }
}
