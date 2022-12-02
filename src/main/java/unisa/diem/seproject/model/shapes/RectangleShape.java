package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.Bounds;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseClosedShape;

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

    public RectangleShape(Point start, Point end) { // used for testing purposes
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor.toFXColor());
        gc.setFill(fillColor.toFXColor());
        gc.strokeRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        gc.fillRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= start.getX() && mouseX <= end.getX() && mouseY >= start.getY() && mouseY <= end.getY();
    }

    @Override
    public void move(double deltaX, double deltaY) {

    }

    @Override
    public void resize(double deltaX, double deltaY) {

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
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        strokeColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        fillColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        start = new Point(in.readDouble(), in.readDouble());
        end = new Point(in.readDouble(), in.readDouble());
    }
}
