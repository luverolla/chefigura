package unisa.diem.swproject.model.commands;

import unisa.diem.swproject.model.BaseCommand;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;

public class PasteShapeCommand extends BaseCommand {
    private final Shape original;

    private Shape cloned;
    private final ShapeManager sm;

    private final boolean cut;

    public PasteShapeCommand(Shape original, boolean cut, ShapeManager sm) {
        this.sm = sm;
        this.original = original;
        this.cut = cut;
    }

    public int execute() throws CloneNotSupportedException {
        cloned = original.clone();
        sm.draw(cloned);

        if(cut)
            sm.resetCopiedShape();

        return 0;
    }

    public int rollback() {
        sm.deleteShape(cloned);

        return 0;
    }
}
