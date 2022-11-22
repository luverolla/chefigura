package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;

public abstract class BaseShape implements Shape {
    private int zIndex;

    @Override
    public int getZIndex() {
        return zIndex;
    }

    @Override
    public void setZIndex(GraphicsContext gc, int zIndex) {
        this.zIndex = zIndex;
    }
}
