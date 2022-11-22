package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Shape extends Cloneable {
    Shape clone();
    boolean contains(double x, double y);

    void draw(GraphicsContext gc);

    void remove(GraphicsContext gc);

    void redraw(GraphicsContext gc, double zoom);

    void rotate(GraphicsContext gc, double angle);

    void translate(GraphicsContext gc, double deltaX, double deltaY);

    void mirror(GraphicsContext gc, boolean vertical, boolean horizontal);

    void scale(GraphicsContext gc, double factorX, double factorY);

    void fill(GraphicsContext gc, Color color);

    void stroke(GraphicsContext gc, double width, Color color);

    int getZIndex();

    void setZIndex(GraphicsContext gc, int zIndex);
}
