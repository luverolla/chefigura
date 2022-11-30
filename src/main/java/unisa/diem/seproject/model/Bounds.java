package unisa.diem.seproject.model;

import javafx.scene.canvas.GraphicsContext;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.extensions.Point;

public class Bounds {

    private Point start;
    private Point end;

    public Bounds(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getCenter() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    public Point[] getAnchorPoints() {
        return new Point[] {
            start,
            new Point(end.getX(), start.getY()),
            end,
            new Point(start.getX(), end.getY())
        };
    }

    public void show(GraphicsContext gc) {
        gc.setFill(new Color(0.2, 0.8, 1, 0.5).toFXColor());
        gc.fillRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        Point center = getCenter();
        gc.setStroke(new Color(0.5, 0.5, 0.5).toFXColor());
        gc.strokeLine(center.getX() - 5, center.getY(), center.getX() +5 , center.getY());
        gc.strokeLine(center.getX(), center.getY() - 5, center.getX() , center.getY() + 5);
        for (Point anchorPoint : getAnchorPoints()) {
            gc.strokeRect(anchorPoint.getX() - 2.5, anchorPoint.getY() - 2.5, 5, 5);
        }
    }

}
