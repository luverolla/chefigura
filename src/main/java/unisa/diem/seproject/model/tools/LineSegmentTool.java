package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.shapes.LineSegmentShape;

/**
 * Tool to draw a line segment
 */
public class LineSegmentTool implements ShapeTool {

    private Point start;
    private Point end;
    private final ShapeManager sm;
    private Shape shape;
    private Color strokeColor;

    public LineSegmentTool(ShapeManager sm) {
        this.sm = sm;
        this.start = null;
        this.end = null;
        this.shape = null;
        this.strokeColor = Color.BLACK;
    }

    private void apply() {
        if (start != null && end != null && sm.getGraphicsContext() != null) {
            start = new Point(start.getX() / sm.getZoomFactor(), start.getY() / sm.getZoomFactor());
            end = new Point(end.getX() / sm.getZoomFactor(), end.getY() / sm.getZoomFactor());
            shape = new LineSegmentShape(strokeColor, start, end);
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
        if (start == null) {
            start = new Point(mouseX, mouseY);
        } else if (end == null) {
            end = new Point(mouseX, mouseY);
            apply();
        }
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if (start != null && end == null) {
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.BLACK.fade(0.5).toFXColor());
            sm.getGraphicsContext().strokeLine(start.getX(), start.getY(), mouseX, mouseY);
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }
}
