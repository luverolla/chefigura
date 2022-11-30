package unisa.diem.seproject.model;

import javafx.scene.canvas.GraphicsContext;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.Bounds;

import java.io.Serializable;

public interface Shape extends Serializable {
    void draw(GraphicsContext gc);
    boolean contains(double mouseX, double mouseY);
    void move(double deltaX, double deltaY);
    void resize(double deltaX, double deltaY);
    void setStrokeColor(Color strokeColor);
    Bounds getBounds();
}
