package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseShape;

public class LineSegmentShape extends BaseShape {

    private final Point2D start;
    private final Point2D end;

    public LineSegmentShape(Color strokeColor, Point2D start, Point2D end) {
        super(strokeColor);
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(strokeColor);
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        gc.restore();
    }
}
