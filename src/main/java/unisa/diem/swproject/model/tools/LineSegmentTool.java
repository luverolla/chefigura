package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.Tool;
import unisa.diem.swproject.model.shapes.LineSegmentShape;

public class LineSegmentTool implements Tool {

    private Point2D start;
    private Point2D end;
    private String hint;
    private final GraphicsContext gc;

    private Shape shape;

    public LineSegmentTool(GraphicsContext gc) {
        this.gc = gc;
        this.start = null;
        this.end = null;
        this.hint = "Select the start point of the line segment";
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(start == null) {
            start = new Point2D(mouseX, mouseY);
            hint = "Select the end point of the line segment";
        } else {
            end = new Point2D(mouseX, mouseY);
            apply();
            hint = "Select the start point of the line segment";
        }
        this.start = new Point2D(mouseX, mouseY);
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {

    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {

    }

    @Override
    public boolean isShapeTool() {
        return true;
    }

    @Override
    public int apply() {
        if(start != null && end != null) {
            shape = new LineSegmentShape(start, end);
            shape.draw(gc);
            start = null;
            end = null;
            return 0;
        }
        return -1;
    }

    @Override
    public int revert() {
        if(shape != null) {
            shape.remove(gc);
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
