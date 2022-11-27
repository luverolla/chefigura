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
    private final ShapeManager sm;
    private Shape shape;

    public LineSegmentTool(ShapeManager sm) {
        this.sm = sm;
        this.start = null;
        this.end = null;
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
        if (start != null && end != null) {
            shape = new LineSegmentShape(start, end);
            sm.draw(shape);
            start = null;
            end = null;
        }
    }
}
