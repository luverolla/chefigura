package unisa.diem.seproject.model;

/**
 * Generic tool
 *<p>
 * Implements the interface "State" of the homonym pattern
 * </p>
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
    void mouseMove(double mouseX, double mouseY);

    /**
     * Resets the tool's internal state (e.g. already chosen points during shapes drawing)
     */
    void reset();
}
