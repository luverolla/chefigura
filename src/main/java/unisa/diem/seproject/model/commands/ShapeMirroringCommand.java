package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;

public class ShapeMirroringCommand implements Command {

    private final ShapeManager sm;
    private final Shape s;
    private final boolean horizontal;

    public ShapeMirroringCommand(ShapeManager sm, Shape s, boolean horizontal) {
        this.sm = sm;
        this.s = s;
        this.horizontal = horizontal;
    }

    @Override
    public void execute() {
        sm.mirror(s, horizontal);
    }

    @Override
    public void rollback() {
        sm.mirror(s, horizontal);
    }
}
