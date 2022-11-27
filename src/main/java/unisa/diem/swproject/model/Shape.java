package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;

public interface Shape extends Cloneable {
    void draw(GraphicsContext gc);
}
