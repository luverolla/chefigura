package unisa.diem.seproject.model.tools;

public interface AnchorPoint {
    void mouseMove(double mouseX, double mouseY);
    void mouseDragStart(double mouseX, double mouseY);
    void mouseDragEnd(double mouseX, double mouseY);
    void mouseDragInProgress(double mouseX, double mouseY);
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
}
