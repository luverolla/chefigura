package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeRotationCommand implements Command {

    private final ShapeManager sm;
    private final Shape shape;
    private final double angle;

    public ShapeRotationCommand(ShapeManager sm, Shape s, double angle) {
        this.sm = sm;
        this.shape = s;
        this.angle = angle;
    }

    @Override
    public void execute() {
        sm.rotate(shape, angle);
    }

    @Override
    public void rollback() {
        sm.rotate(shape, -angle);
    }
}
