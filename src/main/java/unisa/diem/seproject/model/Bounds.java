package unisa.diem.seproject.model;

import javafx.scene.canvas.GraphicsContext;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.extensions.Point;

/**
 * Class for representing the bounds of a shape
 * The bounds are rectangular regions surrounding a shape
 */
public class Bounds {

    private static final int HANDLE_SIZE = 5;
    private static final int HANDLE_OFFSET = HANDLE_SIZE / 2;
    private static final int CROSS_SIZE = 10;
    private static final int CROSS_OFFSET = CROSS_SIZE / 2;

    private final Point start;
    private final Point end;

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

    public boolean mouseOnCenter(double mouseX, double mouseY) {
        Point center = getCenter();
        return (mouseX >= center.getX() - 5 && mouseX <= center.getX() + 5) && (mouseY >= center.getY() - 5 && mouseY <= center.getY() + 5);
    }

    public boolean mouseOnNWAnchorPoint(double mouseX, double mouseY) {
        Point anchorPoint = getAnchorPoints()[0];
        return (mouseX >= anchorPoint.getX() - 5 && mouseX <= anchorPoint.getX() + 5) && (mouseY >= anchorPoint.getY() - 5 && mouseY <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnNEAnchorPoint(double mouseX, double mouseY) {
        Point anchorPoint = getAnchorPoints()[1];
        return (mouseX >= anchorPoint.getX() - 5 && mouseX <= anchorPoint.getX() + 5) && (mouseY >= anchorPoint.getY() - 5 && mouseY <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnSEAnchorPoint(double mouseX, double mouseY) {
        Point anchorPoint = getAnchorPoints()[2];
        return (mouseX >= anchorPoint.getX() - 5 && mouseX <= anchorPoint.getX() + 5) && (mouseY >= anchorPoint.getY() - 5 && mouseY <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnSWAnchorPoint(double mouseX, double mouseY) {
        Point anchorPoint = getAnchorPoints()[3];
        return (mouseX >= anchorPoint.getX() - 5 && mouseX <= anchorPoint.getX() + 5) && (mouseY >= anchorPoint.getY() - 5 && mouseY <= anchorPoint.getY() + 5);
    }

    public void show(GraphicsContext gc) {
        gc.setFill(new Color(0.2, 0.8, 1, 0.1).toFXColor());
        gc.fillRect(start.getX(), start.getY(), end.getX() - start.getX(), end.getY() - start.getY());
        Point center = getCenter();
        gc.setStroke(new Color(0.5, 0.5, 0.5).toFXColor());
        gc.strokeLine(center.getX() - CROSS_OFFSET, center.getY(), center.getX() + CROSS_OFFSET , center.getY());
        gc.strokeLine(center.getX(), center.getY() - CROSS_OFFSET, center.getX() , center.getY() + CROSS_OFFSET);
        gc.setStroke(new Color(0, 0, 0).toFXColor());
        for (Point anchorPoint : getAnchorPoints()) {
            gc.strokeRect(anchorPoint.getX() - HANDLE_OFFSET, anchorPoint.getY() - HANDLE_OFFSET, HANDLE_SIZE, HANDLE_SIZE);
        }
    }
}
