package unisa.diem.swproject.model.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.BaseShape;

public class LineSegmentShape extends BaseShape {

    private Point2D start;
    private Point2D end;

    public LineSegmentShape(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= start.getX() && x <= end.getX() && y >= start.getY() && y <= end.getY();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public void remove(GraphicsContext gc) {
        gc.clearRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
    }

    @Override
    public void redraw(GraphicsContext gc, double zoom) {
        start = new Point2D(start.getX() * zoom, start.getY() * zoom);
        end = new Point2D(end.getX() * zoom, end.getY() * zoom);
        draw(gc);
    }

    @Override
    public void rotate(GraphicsContext gc, double angle) {
        start = new Point2D(start.getX() * Math.cos(angle) - start.getY() * Math.sin(angle), start.getX() * Math.sin(angle) + start.getY() * Math.cos(angle));
        end = new Point2D(end.getX() * Math.cos(angle) - end.getY() * Math.sin(angle), end.getX() * Math.sin(angle) + end.getY() * Math.cos(angle));
    }

    @Override
    public void translate(GraphicsContext gc, double deltaX, double deltaY) {
        start = new Point2D(start.getX() + deltaX, start.getY() + deltaY);
        end = new Point2D(end.getX() + deltaX, end.getY() + deltaY);
    }

    @Override
    public void mirror(GraphicsContext gc, boolean vertical, boolean horizontal) {
        if(vertical) {
            start = new Point2D(-start.getX(), start.getY());
            end = new Point2D(-end.getX(), end.getY());
        }
        if(horizontal) {
            start = new Point2D(start.getX(), -start.getY());
            end = new Point2D(end.getX(), -end.getY());
        }
    }

    @Override
    public void scale(GraphicsContext gc, double factorX, double factorY) {
        start = new Point2D(start.getX() * factorX, start.getY() * factorY);
        end = new Point2D(end.getX() * factorX, end.getY() * factorY);
    }

    @Override
    public void fill(GraphicsContext gc, Color color) {
        throw new RuntimeException("Cannot fill an open shape");
    }

    @Override
    public void stroke(GraphicsContext gc, double width, Color color) {
        remove(gc);
        gc.setLineWidth(width);
        gc.setStroke(color);
        draw(gc);
    }
}
