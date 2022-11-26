package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    @Override
    public abstract Shape clone();

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
