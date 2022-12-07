package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.shapes.RectangleShape;

/**
 * Tool to draw a rectangle
 */
public class RectangleTool implements ClosedShapeTool {

    private Point start;
    private Point end;
    private Shape shape;
    private final ShapeManager sm;
    private Color strokeColor;
    private Color fillColor;

    public RectangleTool(ShapeManager sm) {
        this.sm = sm;
        this.start = null;
        this.end = null;
        this.shape = null;
        this.strokeColor = Color.BLACK;
        this.fillColor = Color.TRANSPARENT;
    }

    private void apply() {
        if(start != null && end != null && sm.getGraphicsContext() != null) {
            start = new Point(start.getX() / sm.getZoomFactor(), start.getY() / sm.getZoomFactor());
            end = new Point(end.getX() / sm.getZoomFactor(), end.getY() / sm.getZoomFactor());
            shape = new RectangleShape(strokeColor, fillColor, start, end);
            sm.drawCommand(shape);
            start = null;
            end = null;
        }
    }

    @Override
    public void reset() {
        start = null;
        end = null;
        shape = null;
        sm.redraw();
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(start == null) {
            start = new Point(mouseX, mouseY);
        } else {
            end = new Point(mouseX, mouseY);
            if(sm.getGraphicsContext() == null) {
                return;
            }
            sm.redraw();
            if(end.getX() < start.getX()) {
                double tmp = start.getX();
                start = new Point(end.getX(), start.getY());
                end = new Point(tmp, end.getY());
            }
            if(end.getY() < start.getY()) {
                double tmp = start.getY();
                start = new Point(start.getX(), end.getY());
                end = new Point(end.getX(), tmp);
            }
            apply();
        }
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if(start != null && end == null) {
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.BLACK.fade(0.5).toFXColor());
            Point currStart = new Point(start.getX(), start.getY()),
                    currEnd = new Point(mouseX, mouseY);
            if(currEnd.getX() < currStart.getX()) {
                double tmp = currStart.getX();
                currStart = new Point(currEnd.getX(), currStart.getY());
                currEnd = new Point(tmp, currEnd.getY());
            }
            if(currEnd.getY() < currStart.getY()) {
                double tmp = currStart.getY();
                currStart = new Point(currStart.getX(), currEnd.getY());
                currEnd = new Point(currEnd.getX(), tmp);
            }
            sm.getGraphicsContext().strokeRect(currStart.getX(), currStart.getY(), currEnd.getX() - currStart.getX(),  currEnd.getY() - currStart.getY());
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
