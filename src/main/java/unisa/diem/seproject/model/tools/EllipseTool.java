package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.shapes.EllipseShape;

/**
 * Tool to draw an ellipse
 */
public class EllipseTool implements ClosedShapeTool {
    private Point center;
    private Point radius;
    private final ShapeManager sm;
    private Shape shape;
    private Color strokeColor;
    private Color fillColor;

    public EllipseTool(ShapeManager sm) {
        this.sm = sm;
        this.center = null;
        this.radius = null;
        this.shape = null;
        this.strokeColor = Color.BLACK;
        this.fillColor = Color.TRANSPARENT;
    }

    private void apply() {
        if(center != null && radius != null && sm.getGraphicsContext() != null) {
            center = new Point(center.getX() / sm.getZoomFactor(), center.getY() / sm.getZoomFactor());
            radius = new Point(radius.getX() / sm.getZoomFactor(), radius.getY() / sm.getZoomFactor());
            double radiusX = Math.abs(center.getX() - radius.getX());
            double radiusY = Math.abs(center.getY() - radius.getY());
            shape = new EllipseShape(strokeColor, fillColor, center, radiusX, radiusY);
            sm.drawCommand(shape);
            center = null;
            radius = null;
        }
    }

    @Override
    public void reset() {
        center = null;
        radius = null;
        shape = null;
        sm.redraw();
    }

    public Point getCenter() {
        return center;
    }

    public Point getEnd() {
        return radius;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(center == null) {
            center = new Point(mouseX, mouseY);
        } else {
            radius = new Point(mouseX, mouseY);
            apply();
        }
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if(center != null && radius == null) {
            Point current = new Point(mouseX, mouseY);
            double radiusX = Math.abs(current.getX() - center.getX());
            double radiusY = Math.abs(current.getY() - center.getY());
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.BLACK.fade(0.5).toFXColor());
            sm.getGraphicsContext().strokeOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
