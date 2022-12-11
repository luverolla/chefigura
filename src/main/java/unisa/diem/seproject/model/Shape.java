package unisa.diem.seproject.model;

import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.extensions.Color;

/**
 * Interface representing a generic shape
 * <p>
 *     Implement the "Component" interface of the "Composite" pattern
 * </p>
 */
public interface Shape extends Serializable, Comparable<Shape> {
    /**
     * Draw the shape on canvas
     * @param gc The graphic context of the canvas to draw on
     * @param zoomFactor The current zoom factor of the canvas
     */
    void draw(GraphicsContext gc, double zoomFactor);

    /**
     * Tells if a given point belongs to the shape
     * @param mouseX The x coordinate of the point to check for
     * @param mouseY The y coordinate of the point to check for
     * @param zoomFactor The current zoom factor of the canvas
     */
    boolean contains(double mouseX, double mouseY, double zoomFactor);

    /**
     * Move the shape by given amounts
     * @param deltaX The amount to move on the x-axis
     * @param deltaY The amount to move on the y-axis
     * @param zoomFactor The current zoom factor of the canvas
     */
    void move(double deltaX, double deltaY, double zoomFactor);

    /**
     * Resize the shape by given amount
     * @param delta The amount to resize on the x-axis
     * @param zoomFactor The current zoom factor of the canvas
     */
    void resize(double delta, double zoomFactor);
    double getAngle();
    void setAngle(double angle);

    /**
     * Rotate by given angle (degrees)
     * @param angle The angle to rotate by (degrees)
     */
    void rotate(double angle);

    /**
     * Stretch the shape by given amounts
     * @param deltaX The amount to stretch on the x-axis
     * @param deltaY The amount to stretch on the y-axis
     * @param direction An integer being 1 if the stretch is on the right or bottom side, -1 if it's on the left or top side
     */
    void stretch(double deltaX, double deltaY, int direction);
    void mirrorHorizontal();
    void mirrorVertical();
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
}
