package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.Bounds;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseShape;
import unisa.diem.seproject.model.extensions.Point;

/**
 * Class that represents a line segment
 */
public class LineSegmentShape extends BaseShape {

    private Point start;
    private Point end;
    private double angle;

    public LineSegmentShape(Color strokeColor, Point start, Point end) {
        super(strokeColor);
        this.start = start;
        this.end = end;
        this.angle = 0;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public void draw(GraphicsContext gc, double zoomFactor) {
        gc.save();
        gc.setStroke(strokeColor.toFXColor());
        gc.translate(getBounds().getCenter().getX() * zoomFactor, getBounds().getCenter().getY() * zoomFactor);
        gc.rotate(angle);
        gc.translate(-getBounds().getCenter().getX() * zoomFactor, -getBounds().getCenter().getY() * zoomFactor);
        gc.strokeLine(start.getX() * zoomFactor, start.getY() * zoomFactor, end.getX() * zoomFactor, end.getY() * zoomFactor);
        gc.restore();
    }

    @Override
    public boolean contains(double mouseX, double mouseY, double zoomFactor) {
        return getBounds().contains(mouseX, mouseY, zoomFactor);
    }

    @Override
    public void move(double deltaX, double deltaY, double zoomFactor) {
        start = new Point(start.getX() + deltaX * zoomFactor, start.getY() + deltaY * zoomFactor);
        end = new Point(end.getX() + deltaX * zoomFactor, end.getY() + deltaY * zoomFactor);
    }

    private void defineStartAndEnd(double newStartX, double newStartY, double newEndX, double newEndY) {
        if (start.getX() < end.getX()) {
            if (start.getY() < end.getY()) {
                start = new Point(newStartX, newStartY);
                end = new Point(newEndX, newEndY);
            } else {
                start = new Point(newStartX, newEndY);
                end = new Point(newEndX, newStartY);
            }
        } else {
            if (start.getY() < end.getY()) {
                start = new Point(newEndX, newStartY);
                end = new Point(newStartX, newEndY);
            } else {
                start = new Point(newEndX, newEndY);
                end = new Point(newStartX, newStartY);
            }
        }
    }

    @Override
    public void resize(double resizeFactor, double zoomFactor) {
        double newWidth = getBounds().getWidth() * resizeFactor * zoomFactor;
        double newHeight = getBounds().getHeight() * resizeFactor * zoomFactor;
        double newStartX = getBounds().getCenter().getX() - newWidth / 2;
        double newStartY = getBounds().getCenter().getY() - newHeight / 2;
        double newEndX = getBounds().getCenter().getX() + newWidth / 2;
        double newEndY = getBounds().getCenter().getY() + newHeight / 2;
        defineStartAndEnd(newStartX, newStartY, newEndX, newEndY);
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void rotate(double angle) {
        this.angle += angle;
    }

    @Override
    public Color getStrokeColor() {
        return strokeColor;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public Bounds getBounds() {
        double minX = Math.min(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxX = Math.max(start.getX(), end.getX());
        double maxY = Math.max(start.getY(), end.getY());
        return new Bounds(this, new Point(minX, minY), new Point(maxX, maxY));
    }

    @Override
    public Shape copy() {
        return new LineSegmentShape(strokeColor, start, end);
    }

    @Override
    public void stretch(double deltaX, double deltaY, int direction) {
        if (direction > 0){
            double newEndX = end.getX() + deltaX;
            double newEndY = end.getY() + deltaY;
            defineStartAndEnd(start.getX(), start.getY(), newEndX, newEndY);
        } else {
            double newStartX = start.getX() + deltaX;
            double newStartY = start.getY() + deltaY;
            defineStartAndEnd(newStartX, newStartY, end.getX(), end.getY());
        }
    }

    @Override
    public void mirrorHorizontal() {
        double newStartX = end.getX();
        double newStartY = start.getY();
        double newEndX = start.getX();
        double newEndY = end.getY();
        start = new Point(newStartX, newStartY);
        end = new Point(newEndX, newEndY);
    }

    @Override
    public void mirrorVertical() {
        double newStartX = start.getX();
        double newStartY = end.getY();
        double newEndX = end.getX();
        double newEndY = start.getY();
        start = new Point(newStartX, newStartY);
        end = new Point(newEndX, newEndY);
    }
}
