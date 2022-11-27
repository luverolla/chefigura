package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseShape;
import unisa.diem.swproject.model.Shape;

public class LineSegmentShape extends BaseShape {

    private Point2D start;
    private Point2D end;

    public LineSegmentShape(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
