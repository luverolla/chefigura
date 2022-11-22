package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;

public class ShapeDeleteCommand extends BaseCommand {
    private final Shape shape;
    private final GraphicsContext gc;

    public ShapeDeleteCommand(Shape shape, GraphicsContext gc) {
        this.gc = gc;
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public int execute() {
        shape.remove(gc);
        return 0;
    }

    @Override
    public int rollback() {
        shape.draw(gc);
        return 0;
    }
}
