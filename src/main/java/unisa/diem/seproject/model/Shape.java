package unisa.diem.seproject.model;

import javafx.scene.canvas.GraphicsContext;
import unisa.diem.seproject.model.extensions.Color;

import java.io.Serializable;

/**
 * Generic shape
 */
public interface Shape extends Serializable {
    /**
     * Draw the shape on canvas
     * @param gc The graphic context of the canvas to draw on
     */
    void draw(GraphicsContext gc);

    /**
     * Tells if a given point belongs to the shape
     * @param mouseX The x coordinate of the point to check for
     * @param mouseY The y coordinate of the point to check for
     */
    boolean contains(double mouseX, double mouseY);

    /**
     * Move the shape by given amounts
     * @param deltaX The amount to move on the x-axis
     * @param deltaY The amount to move on the y-axis
     */
    void move(double deltaX, double deltaY);

    /**
     * Resize the shape by given amounts
     * @param deltaX The amount to resize on the x-axis
     * @param deltaY The amount to resize on the y-axis
     */
    void resize(double deltaX, double deltaY);
    void setStrokeColor(Color strokeColor);
    Bounds getBounds();
}
