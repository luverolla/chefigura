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
    private final Shape s;
    private final Point start;
    private final Point end;
    private final Point center;

    public Bounds(Shape s, Point start, Point end) {
        this.s = s;
        this.start = start;
        this.end = end;
        this.center = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    public Point getCenter() {
        return center;
    }

    public Point[] getAnchorPoints() {
        double radiansAngle = s.getAngle() * Math.PI / 180;
        double centerX = center.getX();
        double centerY = center.getY();
        double cos = Math.cos(radiansAngle);
        double sin = Math.sin(radiansAngle);
        Point N = new Point(start.getX() + getWidth() / 2, start.getY());
        Point E = new Point(end.getX(), start.getY() + getHeight() / 2);
        Point S = new Point(end.getX() - getWidth() / 2, end.getY());
        Point W = new Point(start.getX(), end.getY() - getHeight() / 2);
        Point rotatedN = new Point(centerX + (N.getX() - centerX) * cos - (N.getY() - centerY) * sin,
                centerY + (N.getX() - centerX) * sin + (N.getY() - centerY) * cos);
        Point rotatedE = new Point(centerX + (E.getX() - centerX) * cos - (E.getY() - centerY) * sin,
                centerY + (E.getX() - centerX) * sin + (E.getY() - centerY) * cos);
        Point rotatedS = new Point(centerX + (S.getX() - centerX) * cos - (S.getY() - centerY) * sin,
                centerY + (S.getX() - centerX) * sin + (end.getY() - centerY) * cos);
        Point rotatedW = new Point(centerX + (W.getX() - centerX) * cos - (W.getY() - centerY) * sin,
                centerY + (W.getX() - centerX) * sin + (W.getY() - centerY) * cos);
        return new Point[] {rotatedN, rotatedE, rotatedS, rotatedW};
    }

    public double getWidth() {
        return end.getX() - start.getX();
    }

    public double getHeight() {
        return end.getY() - start.getY();
    }

    public boolean contains(double mouseX, double mouseY, double zoomFactor) {
        Point[] anchorPoints = getAnchorPoints();
        double startX = (anchorPoints[0].getX() - getWidth() / 2);
        double startY = (anchorPoints[3].getY() - getHeight() / 2);
        double endX = (anchorPoints[2].getX() + getWidth() / 2);
        double endY = (anchorPoints[1].getY() + getHeight() / 2);
        return mouseX / zoomFactor >= startX && mouseX / zoomFactor <= endX &&
                mouseY / zoomFactor >= startY && mouseY / zoomFactor <= endY;
    }

    public boolean mouseOnCenter(double mouseX, double mouseY, double zoomFactor) {
        return (mouseX / zoomFactor >= center.getX() - 5 && mouseX / zoomFactor <= center.getX() + 5)
                && (mouseY / zoomFactor >= center.getY() - 5 && mouseY / zoomFactor <= center.getY() + 5);
    }

    public boolean mouseOnNAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[0];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5)
                && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnEAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[1];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5)
                && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnSAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[2];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5)
                && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public boolean mouseOnWAnchorPoint(double mouseX, double mouseY, double zoomFactor) {
        Point anchorPoint = getAnchorPoints()[3];
        return (mouseX / zoomFactor >= anchorPoint.getX() - 5 && mouseX / zoomFactor <= anchorPoint.getX() + 5)
                && (mouseY / zoomFactor >= anchorPoint.getY() - 5 && mouseY / zoomFactor <= anchorPoint.getY() + 5);
    }

    public void show(GraphicsContext gc, double zoomFactor) {
        gc.save();
        for (Point anchorPoint : getAnchorPoints()) {
            gc.strokeRect(zoomFactor * (anchorPoint.getX() - HANDLE_OFFSET), zoomFactor * (anchorPoint.getY() - HANDLE_OFFSET), zoomFactor * HANDLE_SIZE, zoomFactor * HANDLE_SIZE);
        }
        gc.setFill(new Color(0.2, 0.8, 1, 0.1).toFXColor());
        gc.translate(center.getX() * zoomFactor, center.getY() * zoomFactor);
        gc.rotate(s.getAngle());
        gc.translate(-center.getX() * zoomFactor, -center.getY() * zoomFactor);
        gc.fillRect(start.getX() * zoomFactor, start.getY() * zoomFactor, getWidth() * zoomFactor, getHeight() * zoomFactor);
        gc.setStroke(new Color(0.5, 0.5, 0.5).toFXColor());
        gc.strokeLine(zoomFactor * (center.getX() - CROSS_OFFSET), center.getY() * zoomFactor, zoomFactor * (center.getX() + CROSS_OFFSET) , zoomFactor * center.getY());
        gc.strokeLine(zoomFactor * center.getX(), zoomFactor * (center.getY() - CROSS_OFFSET), zoomFactor * center.getX() , zoomFactor * (center.getY() + CROSS_OFFSET));
        gc.setStroke(new Color(0, 0, 0).toFXColor());
        gc.restore();
    }
}
