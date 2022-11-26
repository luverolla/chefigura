package unisa.diem.swproject.model.tools;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.Tool;
import unisa.diem.swproject.model.shapes.RectangleShape;

public class RectangleTool implements Tool {

    private Point2D start;
    private Point2D end;
    private String hint;
    private Shape shape;

    private final ShapeManager sm;

    public RectangleTool(ShapeManager sm) {
        this.sm = sm;
        this.start = null;
        this.end = null;
        this.hint = "Select the start point of the rectangle";
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        if(start == null) {
            start = new Point2D(mouseX, mouseY);
            hint = "Select the end point of the rectangle";
        } else {
            end = new Point2D(mouseX, mouseY);
            sm.redraw();
            if(end.getX() < start.getX()) {
                double tmp = start.getX();
                start = new Point2D(end.getX(), start.getY());
                end = new Point2D(tmp, end.getY());
            }
            if(end.getY() < start.getY()) {
                double tmp = start.getY();
                start = new Point2D(start.getX(), end.getY());
                end = new Point2D(end.getX(), tmp);
            }
            apply();
            hint = "Select the start point of the rectangle";
        }
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {

    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {

        if(start != null && end == null) {
            sm.redraw();
            sm.getGraphicsContext().save();
            sm.getGraphicsContext().setStroke(Color.GRAY);

            Point2D currStart = new Point2D(start.getX(), start.getY()),
                    currEnd = new Point2D(mouseX, mouseY);

            if(currEnd.getX() < currStart.getX()) {
                double tmp = currStart.getX();
                currStart = new Point2D(currEnd.getX(), currStart.getY());
                currEnd = new Point2D(tmp, currEnd.getY());
            }

            if(currEnd.getY() < currStart.getY()) {
                double tmp = currStart.getY();
                currStart = new Point2D(currStart.getX(), currEnd.getY());
                currEnd = new Point2D(currEnd.getX(), tmp);
            }

            sm.getGraphicsContext().strokeRect(currStart.getX(), currStart.getY(), currEnd.getX() - currStart.getX(),  currEnd.getY() - currStart.getY());
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
            shape = new RectangleShape(start, end);
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