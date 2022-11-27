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
    private final ShapeManager sm;
    private Shape shape;

    public EllipseTool(ShapeManager sm) {
        this.sm = sm;
        this.center = null;
        this.shape = null;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(center == null) {
            center = new Point2D(mouseX, mouseY);
        } else {
            radius = new Point2D(mouseX, mouseY );
            apply();
        }
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if(center != null && radius == null) {
            Point2D current = new Point2D(mouseX, mouseY);
            double radiusX = Math.abs(current.getX() - center.getX());
            double radiusY = Math.abs(current.getY() - center.getY());
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.GRAY);
            sm.getGraphicsContext().strokeOval(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2);
            sm.getGraphicsContext().restore();
        }
    }

    @Override
    public void apply() {
        if(center != null) {
            double radiusX = Math.abs(center.getX() - radius.getX());
            double radiusY = Math.abs(center.getY() - radius.getY());
            shape = new EllipseShape(center, radiusX, radiusY);
            sm.draw(shape);
            center = null;
            radius = null;
        }
    }
}
