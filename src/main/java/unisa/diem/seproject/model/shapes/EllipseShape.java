package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.Bounds;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseClosedShape;
import unisa.diem.seproject.model.extensions.Point;

import java.io.Serial;

/**
 *
 */
public class EllipseShape extends BaseClosedShape {

    private Point center;
    private double radiusX;
    private double radiusY;

    public EllipseShape(Color strokeColor, Color fillColor, Point center, double radiusX, double radiusY) {
        super(strokeColor, fillColor);
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor.toFXColor());
        gc.setFill(fillColor.toFXColor());
        gc.strokeOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
        gc.fillOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        return Math.pow(mouseX - center.getX(), 2) / Math.pow(radiusX, 2) + Math.pow(mouseY - center.getY(), 2) / Math.pow(radiusY, 2) <= 1;
    }

    @Override
    public void move(double deltaX, double deltaY) {
        center = new Point(center.getX() + deltaX, center.getY() + deltaY);
    }

    @Override
    public void resize(double delta) {
        double ratio = getBounds().getWidth() / getBounds().getHeight();
        double newWidth = getBounds().getWidth() + delta;
        double newHeight = newWidth / ratio;
        radiusX = newWidth / 2;
        radiusY = newHeight / 2;
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
        double startX = center.getX() - radiusX;
        double startY = center.getY() - radiusY;
        double endX = center.getX() + radiusX;
        double endY = center.getY() + radiusY;
        return new Bounds(new Point(startX, startY), new Point(endX, endY));
    }

    @Override
    public Shape copy() {
        return new EllipseShape(strokeColor, fillColor, center, radiusX, radiusY);
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
        out.writeDouble(center.getX());
        out.writeDouble(center.getY());
        out.writeDouble(radiusX);
        out.writeDouble(radiusY);
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        strokeColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        fillColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
        center = new Point(in.readDouble(), in.readDouble());
        radiusX = in.readDouble();
        radiusY = in.readDouble();
    }
}
