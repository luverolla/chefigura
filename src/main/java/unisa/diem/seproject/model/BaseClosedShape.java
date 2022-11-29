package unisa.diem.seproject.model;

import unisa.diem.seproject.model.extensions.Color;

public abstract class BaseClosedShape extends BaseShape implements ClosedShape {
    protected Color fillColor;

    public BaseClosedShape(Color strokeColor, Color fillColor) {
        super(strokeColor);
        this.fillColor = fillColor;
    }

    public BaseClosedShape() {
        super();
        this.fillColor = Color.TRANSPARENT;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}