package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseShape;

public class RectangleShape extends BaseShape {

    private final Point2D start;
    private final Point2D end;

    public RectangleShape(Color strokeColor, Point2D start, Point2D end) {
        super(strokeColor);
        this.start = start;
        this.end = end;
    }

    public RectangleShape(Point2D start, Point2D end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(strokeColor);
        gc.strokeRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        gc.restore();
    }
}
