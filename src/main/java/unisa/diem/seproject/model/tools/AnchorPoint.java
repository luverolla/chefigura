package unisa.diem.seproject.model.tools;

public interface AnchorPoint {
    void mouseMove(double mouseX, double mouseY);
    void mouseDragStart(double mouseX, double mouseY);
    void mouseDragEnd(double mouseX, double mouseY);
    void mouseDragInProgress(double mouseX, double mouseY);
}
