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

    public double getWidth() {
        return end.getX() - start.getX();
    }

    public double getHeight() {
        return end.getY() - start.getY();
    }

    public boolean mouseOnCenter(double mouseX, double mouseY, double zoomFactor) {
        Point center = getCenter();
        return (mouseX / zoomFactor >= center.getX() - 5 && mouseX / zoomFactor <= center.getX() + 5) && (mouseY / zoomFactor >= center.getY() - 5 && mouseY / zoomFactor <= center.getY() + 5);
    }

    public boolean mouseOnNWAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[0];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5) && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnNEAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[1];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5) && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnSEAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[2];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5) && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnSWAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[3];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5) && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public void show(GraphicsContext gc, double zoomFactor) {
        gc.setFill(new Color(0.2, 0.8, 1, 0.1).toFXColor());
        gc.fillRect(start.getX() * zoomFactor, start.getY() * zoomFactor, getWidth() * zoomFactor, getHeight() * zoomFactor);
        Point center = getCenter();
        gc.setStroke(new Color(0.5, 0.5, 0.5).toFXColor());
        gc.strokeLine(zoomFactor * (center.getX() - CROSS_OFFSET), center.getY() * zoomFactor, zoomFactor * (center.getX() + CROSS_OFFSET) , zoomFactor * center.getY());
        gc.strokeLine(zoomFactor * center.getX(), zoomFactor * (center.getY() - CROSS_OFFSET), zoomFactor * center.getX() , zoomFactor * (center.getY() + CROSS_OFFSET));
        gc.setStroke(new Color(0, 0, 0).toFXColor());
        for (Point anchorPoint : getAnchorPoints()) {
            gc.strokeRect(zoomFactor * (anchorPoint.getX() - HANDLE_OFFSET), zoomFactor * (anchorPoint.getY() - HANDLE_OFFSET), zoomFactor * HANDLE_SIZE, zoomFactor * HANDLE_SIZE);
        }
    }
}
