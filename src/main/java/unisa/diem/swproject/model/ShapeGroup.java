package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShapeGroup extends BaseShape {
    private Iterable<Shape> shapes;

    public ShapeGroup(Iterable<Shape> shapes) {
        this.shapes = shapes;
    }

    public Iterable<Shape> getShapes() {
        return shapes;
    }

    @Override
    public Shape clone() {
        return (Shape)super.clone();
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public void remove(GraphicsContext gc) {

    }

    @Override
    public void redraw(GraphicsContext gc, double zoom) {

    }

    @Override
    public void rotate(GraphicsContext gc, double angle) {

    }

    @Override
    public void translate(GraphicsContext gc, double deltaX, double deltaY) {

    }

    @Override
    public void mirror(GraphicsContext gc, boolean vertical, boolean horizontal) {

    }

    @Override
    public void scale(GraphicsContext gc, double factorX, double factorY) {

    }

    @Override
    public void fill(GraphicsContext gc, Color color) {

    }

    @Override
    public void stroke(GraphicsContext gc, double width, Color color) {

    }

    @Override
    public int getZIndex() {
        return 0;
    }

    @Override
    public void setZIndex(GraphicsContext gc, int zIndex) {

    }
}
