package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.Tool;
import unisa.diem.swproject.model.shapes.EllipseShape;

public class EllipseTool implements Tool {

    private Point2D center;
    private Point2D radius;
    private String hint;
    private final ShapeManager sm;
    private Shape shape;

    public EllipseTool(ShapeManager sm) {
        this.sm = sm;
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
            radius = new Point2D(mouseX, mouseY );
            apply();
            hint = "Select the center point of the ellipse";
        }
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {}

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if(center != null && radius == null) {
            Point2D current = new Point2D(mouseX, mouseY);
            double radiusX = Math.abs(current.getX() - center.getX());
            double radiusY = Math.abs(current.getY() - center.getY());
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.GRAY);
            sm.getGraphicsContext().strokeOval(center.getX(), center.getY(), radiusX, radiusY);
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public boolean isShapeTool() {
        return true;
    }

    @Override
    public int apply() {
        double radiusX = Math.abs(center.getX() - radius.getX());
        double radiusY = Math.abs(center.getY() - radius.getY());
        if(center != null) {
            shape = new EllipseShape(center, radiusX, radiusY);
            sm.draw(shape);
            center = null;
            radius = null;
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