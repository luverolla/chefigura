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
        return getBounds().contains(mouseX, mouseY, zoomFactor);
    }

    @Override
    public void move(double deltaX, double deltaY, double zoomFactor) {
        center = new Point(center.getX() + deltaX * zoomFactor, center.getY() + deltaY * zoomFactor);
    }

    @Override
    public void resize(double resizeFactor, double zoomFactor) {
        radiusX = radiusX * resizeFactor * zoomFactor;
        radiusY = radiusY * resizeFactor * zoomFactor;
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
        double startX = center.getX() - radiusX;
        double startY = center.getY() - radiusY;
        double endX = center.getX() + radiusX;
        double endY = center.getY() + radiusY;
        return new Bounds(this, new Point(startX, startY), new Point(endX, endY));
    }

    @Override
    public Shape copy() {
        return new EllipseShape(strokeColor, fillColor, center, radiusX, radiusY);
    }

    @Override
    public void stretch(double deltaX, double deltaY, int direction) {
        if  (direction > 0) {
            radiusX += deltaX / 2;
            radiusY += deltaY / 2;
            center = new Point(center.getX() + deltaX / 2, center.getY() + deltaY / 2);
        }
        else {
            radiusX -= deltaX / 2;
            radiusY -= deltaY / 2;
            center = new Point(center.getX() + deltaX / 2, center.getY() + deltaY / 2);
        }
    }

    @Override
    public void mirrorHorizontal() {
        /*
         * this method is empty because the ellipse is symmetric with respect to the horizontal axis
         */
    }

    @Override
    public void mirrorVertical() {
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
