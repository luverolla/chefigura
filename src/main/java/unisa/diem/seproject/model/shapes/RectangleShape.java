package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.Bounds;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseClosedShape;

import java.io.IOException;
import java.io.Serial;

/**
 * Class that represents a rectangle
 */
public class RectangleShape extends BaseClosedShape {

    private Point start;
    private Point end;

    public RectangleShape(Color strokeColor, Color fillColor, Point start, Point end) {
        super(strokeColor, fillColor);
        this.start = start;
        this.end = end;
    }

    public RectangleShape(Point start, Point end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc, double zoomFactor) {
        gc.setStroke(strokeColor.toFXColor());
        gc.setFill(fillColor.toFXColor());
        double startX = start.getX() * zoomFactor,
                startY = start.getY() * zoomFactor,
                width = Math.abs(start.getX() - end.getX()) * zoomFactor,
                height = Math.abs(start.getY() - end.getY()) * zoomFactor;
        gc.strokeRect(startX, startY, width, height);
        gc.fillRect(startX, startY, width, height);
    }

    @Override
    public boolean contains(double mouseX, double mouseY, double zoomFactor) {
        return mouseX / zoomFactor >= start.getX() && mouseX / zoomFactor <= end.getX() && mouseY / zoomFactor >= start.getY() && mouseY / zoomFactor <= end.getY();
    }

    @Override
    public void move(double deltaX, double deltaY, double zoomFactor) {
        start= new Point(start.getX() + deltaX * zoomFactor, start.getY() + deltaY * zoomFactor);
        end = new Point(end.getX() + deltaX * zoomFactor, end.getY() + deltaY * zoomFactor);
    }

    @Override
    public void resize(double delta, double zoomFactor) {
        double ratio = getBounds().getWidth() / getBounds().getHeight();
        double newWidth = getBounds().getWidth() + delta * zoomFactor;
        double newHeight = newWidth / ratio;
        double newStartX = getBounds().getCenter().getX() - newWidth / 2;
        double newStartY = getBounds().getCenter().getY() - newHeight / 2;
        start = new Point(newStartX, newStartY);
        end = new Point(newStartX + newWidth, newStartY + newHeight);
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
        return new Bounds(start, end);
    }

    @Override
    public Shape copy() {
        return new RectangleShape(strokeColor, fillColor, start, end);
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
        out.writeDouble(strokeColor.getRed());
        out.writeDouble(strokeColor.getGreen());
        out.writeDouble(strokeColor.getBlue());
        out.writeDouble(strokeColor.getOpacity());
        out.writeDouble(fillColor.getRed());
        out.writeDouble(fillColor.getGreen());
        out.writeDouble(fillColor.getBlue());
        out.writeDouble(fillColor.getOpacity());
        out.writeDouble(start.getX());
        out.writeDouble(start.getY());
        out.writeDouble(end.getX());
        out.writeDouble(end.getY());
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        strokeColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        fillColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        start = new Point(in.readDouble(), in.readDouble());
        end = new Point(in.readDouble(), in.readDouble());
    }
}
