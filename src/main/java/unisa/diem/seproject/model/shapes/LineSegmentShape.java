package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.Bounds;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseShape;
import unisa.diem.seproject.model.extensions.Point;

import java.io.Serial;

/**
 * Class that represents a line segment
 */
public class LineSegmentShape extends BaseShape {

    private Point start;
    private Point end;

    public LineSegmentShape(Color strokeColor, Point start, Point end) {
        super(strokeColor);
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor.toFXColor());
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        double startX = start.getX();
        double startY = start.getY();
        double endX = end.getX();
        double endY = end.getY();
        double minX = Math.min(startX, endX);
        double minY = Math.min(startY, endY);
        double maxX = Math.max(startX, endX);
        double maxY = Math.max(startY, endY);
        return (mouseX >= minX && mouseX <= maxX && mouseY >= minY && mouseY <= maxY);
    }

    @Override
    public void move(double deltaX, double deltaY) {
        start = new Point(start.getX() + deltaX, start.getY() + deltaY);
        end = new Point(end.getX() + deltaX, end.getY() + deltaY);
    }

    @Override
    public void resize(double delta) {
        double ratio = getBounds().getWidth() / getBounds().getHeight();
        double newWidth = getBounds().getWidth() + delta;
        double newHeight = newWidth / ratio;
        double newStartX = getBounds().getCenter().getX() - newWidth / 2;
        double newStartY = getBounds().getCenter().getY() - newHeight / 2;
        double newEndX = getBounds().getCenter().getX() + newWidth / 2;
        double newEndY = getBounds().getCenter().getY() + newHeight / 2;
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
    public Color getStrokeColor() {
        return strokeColor;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public Bounds getBounds() {
        double startX = start.getX();
        double startY = start.getY();
        double endX = end.getX();
        double endY = end.getY();
        double minX = Math.min(startX, endX);
        double minY = Math.min(startY, endY);
        double maxX = Math.max(startX, endX);
        double maxY = Math.max(startY, endY);
        return new Bounds(new Point(minX, minY), new Point(maxX, maxY));
    }

    @Override
    public Shape copy() {
        return new LineSegmentShape(strokeColor, start, end);
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
        out.writeDouble(strokeColor.getRed());
        out.writeDouble(strokeColor.getGreen());
        out.writeDouble(strokeColor.getBlue());
        out.writeDouble(strokeColor.getOpacity());
        out.writeDouble(start.getX());
        out.writeDouble(start.getY());
        out.writeDouble(end.getX());
        out.writeDouble(end.getY());
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        strokeColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        start = new Point(in.readDouble(), in.readDouble());
        end = new Point(in.readDouble(), in.readDouble());
    }
}
