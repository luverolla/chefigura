package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeCutCommand implements Command {
    private final ShapeManager sm;
    private final Shape shape;
    private Shape cutted;

    public ShapeCutCommand(ShapeManager sm, Shape shape) {
        this.sm = sm;
        this.shape = shape;
        this.cutted = null;
    }

    @Override
    public void execute() {
        if(shape != null) {
            cutted = shape.copy();
            sm.copiedShapeProperty.set(cutted);
            sm.remove(shape);
        }
    }

    @Override
    public void rollback() {
        sm.add(shape);
    }
}
