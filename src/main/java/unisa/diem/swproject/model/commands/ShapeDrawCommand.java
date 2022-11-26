package unisa.diem.swproject.model.commands;

import unisa.diem.swproject.model.BaseCommand;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;

public class ShapeDrawCommand extends BaseCommand { ;
    private final Shape shape;

    private final ShapeManager sm;

    public ShapeDrawCommand(Shape s, ShapeManager sm) {
        this.shape = s;
        this.sm = sm;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public int execute() {
        sm.add(shape);
        shape.draw(sm.getGraphicsContext());
        return 0;
    }

    @Override
    public int rollback() {
        sm.remove(shape);
        sm.redraw();
        return 0;
    }
}
