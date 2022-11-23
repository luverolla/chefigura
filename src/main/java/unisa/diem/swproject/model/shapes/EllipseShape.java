package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseShape;

public class EllipseShape extends BaseShape {

    private Point2D center;
    private double radiusX;
    private double radiusY;

    public EllipseShape(Point2D center, double radiusX, double radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public boolean contains(double x, double y) {
        return Math.pow(x - center.getX(), 2) / Math.pow(radiusX, 2) + Math.pow(y - center.getY(), 2) / Math.pow(radiusY, 2) <= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
    }

    @Override
    public void remove(GraphicsContext gc) {
        gc.clearRect(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
    }

    @Override
    public void redraw(GraphicsContext gc, double zoom) {
        center = new Point2D(center.getX() * zoom, center.getY() * zoom);
        radiusX *= zoom;
        radiusY *= zoom;
        draw(gc);
    }

    @Override
    public void rotate(GraphicsContext gc, double angle) {
        center = new Point2D(center.getX() * Math.cos(angle) - center.getY() * Math.sin(angle), center.getX() * Math.sin(angle) + center.getY() * Math.cos(angle));
    }

    @Override
    public void translate(GraphicsContext gc, double deltaX, double deltaY) {
        center = new Point2D(center.getX() + deltaX, center.getY() + deltaY);
    }

    @Override
    public void mirror(GraphicsContext gc, boolean vertical, boolean horizontal) {
        if(vertical) {
            center = new Point2D(-center.getX(), center.getY());
        }
        if(horizontal) {
            center = new Point2D(center.getX(), -center.getY());
        }
    }

    @Override
    public void scale(GraphicsContext gc, double factorX, double factorY) {
        radiusX *= factorX;
        radiusY *= factorY;
        draw(gc);
    }

    @Override
    public void fill(GraphicsContext gc, Color color) {
        remove(gc);
        gc.setFill(color);
        gc.fillOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
    }

    @Override
    public void stroke(GraphicsContext gc, double width, Color color) {
        remove(gc);
        gc.setStroke(color);
        gc.setLineWidth(width);
        draw(gc);
    }
}
