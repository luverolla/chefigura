package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.Tool;
import unisa.diem.swproject.model.shapes.LineSegmentShape;

public class LineSegmentTool implements Tool {

    private Point2D start;
    private Point2D end;
    private String hint;
    private final ShapeManager sm;

    private Shape shape;

    public LineSegmentTool(ShapeManager sm) {
        this.sm = sm;
        this.start = null;
        this.end = null;
        this.hint = "Select the start point of the line segment";
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(start == null) {
            start = new Point2D(mouseX, mouseY);
            hint = "Select the end point of the line segment";
        } else if(end == null) {
            end = new Point2D(mouseX, mouseY);
            apply();
            hint = "Select the start point of the line segment";
        }
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {

    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if(start != null && end == null) {
            sm.redraw(); // Clear the canvas and redraw all the shapes on it (without the current one)
            sm.getGraphicsContext().save(); // Save the current state of the canvas
            sm.getGraphicsContext().setStroke(Color.GRAY); // Set the color of the line to gray
            sm.getGraphicsContext().strokeLine(start.getX(), start.getY(), mouseX, mouseY); // Draw the line segment on the canvas
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public boolean isShapeTool() {
        return true;
    }

    @Override
    public int apply() {
        if(start != null && end != null) {
            shape = new LineSegmentShape(start, end);
            sm.draw(shape);
            start = null;
            end = null;
            return 0;
        }
        return -1;
    }

    @Override
    public int revert() {
        if(shape != null) {
            sm.deleteShape(shape);
            shape = null;
            return 0;
        }
        return -1;
    }

    @Override
    public String getCurrentHint() {
        return hint;
    }

    @Override
    public Shape getShape() {
        return shape;
    }
}