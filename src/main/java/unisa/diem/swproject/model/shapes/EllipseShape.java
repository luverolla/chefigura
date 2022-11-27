package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseShape;

public class EllipseShape extends BaseShape {

    private final Point2D center;
    private final double radiusX;
    private final double radiusY;
    public EllipseShape(Color strokeColor, Point2D center, double radiusX, double radiusY) {
        super(strokeColor);
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public EllipseShape(Point2D center, double radiusX, double radiusY) {
        super();
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(strokeColor);
        gc.strokeOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
        gc.restore();
    }
}
