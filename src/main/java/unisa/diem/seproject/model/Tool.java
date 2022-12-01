package unisa.diem.seproject.model;

/**
 * Generic tool
 *
 * Implements the State pattern
 */
public interface Tool {
    /**
     * Actions to be executed when the mouse is pressed
     * @param mouseX x coordinate of the mouse
     * @param mouseY y coordinate of the mouse
     */
    void mouseDown(double mouseX, double mouseY);

    /**
     * Actions to be executed when the mouse is moved
     * @param mouseX x coordinate of the mouse
     * @param mouseY y coordinate of the mouse
     */
    void mouseDrag(double mouseX, double mouseY);

    /**
     * Actions to be executed when the mouse is released
     * @param mouseX x coordinate of the mouse
     * @param mouseY y coordinate of the mouse
     */
    void mouseUp(double mouseX, double mouseY);

    /**
     * Own tool actions
     */
    void apply();

    /**
     * Clear the tool's state
     */
    void reset();
}
