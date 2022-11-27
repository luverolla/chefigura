package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.shapes.EllipseShape;

public class EllipseTool implements ShapeTool {

    private Point2D center;
    private Point2D radius;
    private final ShapeManager sm;
    private Shape shape;
    private Color strokeColor;

    public EllipseTool(ShapeManager sm, Color strokeColor) {
        this.sm = sm;
        this.center = null;
        this.radius = null;
        this.shape = null;
        this.strokeColor = strokeColor;
    }

    public EllipseTool(ShapeManager sm) {
        this.sm = sm;
        this.center = null;
        this.radius = null;
        this.shape = null;
        this.strokeColor = Color.BLACK;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(center == null) {
            center = new Point2D(mouseX, mouseY);
        } else {
            radius = new Point2D(mouseX, mouseY );
            apply();
        }
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if(center != null && radius == null) {
            Point2D current = new Point2D(mouseX, mouseY);
            double radiusX = Math.abs(current.getX() - center.getX());
            double radiusY = Math.abs(current.getY() - center.getY());
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.GRAY);
            sm.getGraphicsContext().strokeOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public void apply() {
        if(center != null && radius != null && sm.getGraphicsContext() != null) {
            double radiusX = Math.abs(center.getX() - radius.getX());
            double radiusY = Math.abs(center.getY() - radius.getY());
            shape = new EllipseShape(strokeColor, center, radiusX, radiusY);
            sm.draw(shape);
            center = null;
            radius = null;
        }
    }
    public Point2D getCenter() {
        return center;
    }
    public Point2D getEnd() {
        return radius;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }
}
