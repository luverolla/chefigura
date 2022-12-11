package unisa.diem.seproject.model;

import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.extensions.Color;

import java.io.Serializable;

/**
 * Generic shape
 */
public interface Shape extends Serializable, Comparable<Shape> {
    /**
     * Draw the shape on canvas
     * @param gc The graphic context of the canvas to draw on
     */
    void draw(GraphicsContext gc, double zoomFactor);
    /**
     * Tells if a given point belongs to the shape
     * @param mouseX The x coordinate of the point to check for
     * @param mouseY The y coordinate of the point to check for
     */
    boolean contains(double mouseX, double mouseY, double zoomFactor);
    /**
     * Move the shape by given amounts
     * @param deltaX The amount to move on the x-axis
     * @param deltaY The amount to move on the y-axis
     */
    void move(double deltaX, double deltaY, double zoomFactor);
    /**
     * Resize the shape by given amount
     * @param delta The amount to resize on the x-axis
     */
    void resize(double delta, double zoomFactor);
    double getAngle();
    void setAngle(double angle);
    void rotate(double angle);
    Color getStrokeColor();
    void setStrokeColor(Color strokeColor);
    Bounds getBounds();
    Shape copy();
    int getZIndex();
    void setZIndex(int zIndex);
    @Override
    default int compareTo(Shape o) {
        return getZIndex() - o.getZIndex();
    }
    void stretch(double deltaX, double deltaY, int direction);
    void mirrorHorizontal();
    void mirrorVertical();
}
