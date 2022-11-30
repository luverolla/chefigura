package unisa.diem.seproject.model.shapes;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.BaseShape;
import unisa.diem.seproject.model.extensions.Point;

import java.io.Serial;

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
        gc.save();
        gc.setStroke(strokeColor.toFXColor());
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        gc.restore();
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= start.getX() && mouseX <= end.getX() && mouseY <= start.getY() && mouseY >= end.getY();
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
