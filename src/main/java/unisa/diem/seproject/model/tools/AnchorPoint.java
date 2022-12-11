package unisa.diem.seproject.model.tools;

/**
 * Interface that represent an anchor point
 * <p>
 *     These points are used as handles to change shape layout properties (move, stretch)
 * </p>
 */
public interface AnchorPoint {
    void mouseMove(double mouseX, double mouseY);
    void mouseDragStart(double mouseX, double mouseY);
    void mouseDragEnd(double mouseX, double mouseY);
    void mouseDragInProgress(double mouseX, double mouseY);
}
