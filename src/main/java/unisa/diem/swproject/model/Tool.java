package unisa.diem.swproject.model;

public interface Tool {
    void mouseDown(double mouseX, double mouseY);

    void mouseUp(double mouseX, double mouseY);

    void mouseDrag(double mouseX, double mouseY);

    boolean isShapeTool();

    int apply();

    int revert();

    Shape getShape();
}
