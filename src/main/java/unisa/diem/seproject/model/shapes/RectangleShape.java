package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseClosedShape;

import java.io.Serial;

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
        gc.save();
        gc.setStroke(strokeColor.toFXColor());
        gc.setFill(fillColor.toFXColor());
        gc.strokeRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        gc.fillRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        gc.restore();
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
