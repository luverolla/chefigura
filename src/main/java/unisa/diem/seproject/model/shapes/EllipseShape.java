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
    private double angle;

    public EllipseShape(Color strokeColor, Color fillColor, Point center, double radiusX, double radiusY) {
        super(strokeColor, fillColor);
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(GraphicsContext gc, double zoomFactor) {
        gc.save();
        gc.setStroke(strokeColor.toFXColor());
        gc.setFill(fillColor.toFXColor());
        double leftX = (center.getX() - radiusX) * zoomFactor,
                topY = (center.getY() - radiusY) * zoomFactor,
                width = 2 * this.radiusX * zoomFactor,
                height = 2 * this.radiusY * zoomFactor;
        gc.translate(getBounds().getCenter().getX() * zoomFactor, getBounds().getCenter().getY() * zoomFactor);
        gc.rotate(angle);
        gc.translate(-getBounds().getCenter().getX() * zoomFactor, -getBounds().getCenter().getY() * zoomFactor);
        gc.strokeOval(leftX, topY, width, height);
        gc.fillOval(leftX, topY, width, height);
        gc.restore();
    }

    @Override
    public boolean contains(double mouseX, double mouseY, double zoomFactor) {
        return Math.pow(mouseX / zoomFactor - center.getX(), 2) / Math.pow(radiusX, 2) + Math.pow(mouseY / zoomFactor - center.getY(), 2) / Math.pow(radiusY, 2) <= 1;
    }

    @Override
    public void move(double deltaX, double deltaY, double zoomFactor) {
        center = new Point(center.getX() + deltaX * zoomFactor, center.getY() + deltaY * zoomFactor);
    }

    @Override
    public void resize(double delta, double zoomFactor) {
        double ratio = getBounds().getWidth() / getBounds().getHeight();
        double newWidth = getBounds().getWidth() + delta * zoomFactor;
        double newHeight = newWidth / ratio;
        radiusX = newWidth / 2;
        radiusY = newHeight / 2;
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
