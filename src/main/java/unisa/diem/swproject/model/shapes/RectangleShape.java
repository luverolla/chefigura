package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseClosedShape;

public class RectangleShape extends BaseClosedShape {

    private final Point2D start;
    private final Point2D end;

    public RectangleShape(Color strokeColor, Color fillColor, Point2D start, Point2D end) {
        super(strokeColor, fillColor);
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
        gc.setFill(fillColor);
        gc.strokeRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        gc.fillRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        gc.restore();
    }
}
