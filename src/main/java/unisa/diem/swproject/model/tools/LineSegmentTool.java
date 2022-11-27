package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.shapes.LineSegmentShape;

public class LineSegmentTool implements ShapeTool {

    private Point2D start;
    private Point2D end;
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

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if (start == null) {
            start = new Point2D(mouseX, mouseY);
        } else if (end == null) {
            end = new Point2D(mouseX, mouseY);
            apply();
        }
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if (start != null && end == null) {
            sm.redraw(); // Clear the canvas and redraw all the shapes on it (without the current one)
            sm.getGraphicsContext().save(); // Save the current state of the canvas
            sm.getGraphicsContext().setStroke(Color.GRAY); // Set the color of the line to gray
            sm.getGraphicsContext().strokeLine(start.getX(), start.getY(), mouseX, mouseY); // Draw the line segment on the canvas
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public void apply() {
        if (start != null && end != null && sm.getGraphicsContext() != null) {
            shape = new LineSegmentShape(strokeColor, start, end);
            sm.draw(shape);
            start = null;
            end = null;
        }
    }

    public Point2D getStart() {
        return start;
    }
    public Point2D getEnd() {
        return end;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }
}
