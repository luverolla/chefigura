package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapePasteCommand implements Command {
    private final ShapeManager sm;
    private final Shape shape;
    private Shape pasted;

    public ShapePasteCommand(ShapeManager sm, Shape shape) {
        this.sm = sm;
        this.shape = shape;
        this.pasted = null;
    }

    public Shape getPasted() {
        return pasted;
    }

    @Override
    public void execute() {
        if(shape != null) {
            pasted = shape.copy();
            sm.add(pasted);
        }
    }

    @Override
    public void rollback() {
        sm.remove(pasted);
    }
}

