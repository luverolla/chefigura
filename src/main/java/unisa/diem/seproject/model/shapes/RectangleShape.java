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
    private double angle;

    public RectangleShape(Color strokeColor, Color fillColor, Point start, Point end) {
        super(strokeColor, fillColor);
        this.start = start;
        this.end = end;
        this.angle = 0;
    }

    public RectangleShape(Point start, Point end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc, double zoomFactor) {
        gc.save();
        gc.setStroke(strokeColor.toFXColor());
        gc.setFill(fillColor.toFXColor());
        double startX = start.getX() * zoomFactor,
                startY = start.getY() * zoomFactor,
                width = Math.abs(start.getX() - end.getX()) * zoomFactor,
                height = Math.abs(start.getY() - end.getY()) * zoomFactor;
        gc.translate(getBounds().getCenter().getX() * zoomFactor, getBounds().getCenter().getY() * zoomFactor);
        gc.rotate(angle);
        gc.translate(-getBounds().getCenter().getX() * zoomFactor, -getBounds().getCenter().getY() * zoomFactor);
        gc.strokeRect(startX, startY, width, height);
        gc.fillRect(startX, startY, width, height);
        gc.restore();
    }

    @Override
    public boolean contains(double mouseX, double mouseY, double zoomFactor) {
        return getBounds().contains(mouseX, mouseY, zoomFactor);
    }

    @Override
    public void move(double deltaX, double deltaY, double zoomFactor) {
        start= new Point(start.getX() + deltaX * zoomFactor, start.getY() + deltaY * zoomFactor);
        end = new Point(end.getX() + deltaX * zoomFactor, end.getY() + deltaY * zoomFactor);
    }

    @Override
    public void resize(double resizeFactor, double zoomFactor) {
        double newStartX = (getBounds().getCenter().getX() - getBounds().getWidth() * resizeFactor / 2) * zoomFactor;
        double newStartY = (getBounds().getCenter().getY() - getBounds().getHeight() * resizeFactor / 2) * zoomFactor;
        double newEndX = (getBounds().getCenter().getX() + getBounds().getWidth() * resizeFactor / 2) * zoomFactor;
        double newEndY = (getBounds().getCenter().getY() + getBounds().getHeight() * resizeFactor / 2) * zoomFactor;
        start = new Point(newStartX, newStartY);
        end = new Point(newEndX, newEndY);
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
        return new Bounds(this, start, end);
    }

    @Override
    public Shape copy() {
        return new RectangleShape(strokeColor, fillColor, start, end);
    }

    @Override
    public void stretch(double deltaX, double deltaY, int direction) {
        if(direction > 0){
            end = new Point(end.getX() + deltaX, end.getY() + deltaY);
        } else {
            start = new Point(start.getX() + deltaX, start.getY() + deltaY);
        }
    }

    @Override
    public void mirrorHorizontal() {
        /*
        * this method is empty because the rectangle is symmetric with respect to the horizontal axis
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
