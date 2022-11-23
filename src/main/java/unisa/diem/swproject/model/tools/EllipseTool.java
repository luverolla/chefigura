package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.Tool;
import unisa.diem.swproject.model.shapes.EllipseShape;

public class EllipseTool implements Tool {

    private Point2D center;
    private double radiusX;
    private double radiusY;
    private String hint;
    private final GraphicsContext gc;
    private Shape shape;

    public EllipseTool(GraphicsContext gc) {
        this.gc = gc;
        this.center = null;
        this.shape = null;
        this.hint = "Select the center point of the ellipse";
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(center == null) {
            center = new Point2D(mouseX, mouseY);
            hint = "Select the end point of the ellipse";
        } else {
            radiusX = Math.abs(mouseX - center.getX());
            radiusY = Math.abs(mouseY - center.getY());
            apply();
            hint = "Select the center point of the ellipse";
        }
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
        if(center != null) {
            shape = new EllipseShape(center, radiusX, radiusY);
            shape.draw(gc);
            center = null;
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
