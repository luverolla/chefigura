package unisa.diem.swproject.model.commands;

import unisa.diem.swproject.model.Command;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;

public class ShapeDrawCommand implements Command {
    private final Shape shape;
    private final ShapeManager sm;

    public ShapeDrawCommand(Shape s, ShapeManager sm) {
        this.shape = s;
        this.sm = sm;
    }

    @Override
    public void execute() {
        sm.add(shape);
    }
}
