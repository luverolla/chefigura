package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;

public class ShapeDeleteCommand {
    private final Shape shape;
    private final GraphicsContext gc;

    public ShapeDeleteCommand(Shape shape, GraphicsContext gc) {
        this.gc = gc;
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    public void execute() {
        shape.remove(gc);
    }

    public void rollback() {
        shape.draw(gc);
    }
}
