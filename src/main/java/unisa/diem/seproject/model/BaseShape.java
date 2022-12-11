package unisa.diem.seproject.model;

import unisa.diem.seproject.model.extensions.Color;

/**
 * Abstract class representing generic shapes
 */
public abstract class BaseShape implements Shape {
    protected Color strokeColor;
    private int zIndex;

    protected BaseShape(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    protected BaseShape() {
        this.strokeColor = Color.BLACK;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }
}
