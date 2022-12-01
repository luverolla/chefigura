package unisa.diem.seproject.model;

import unisa.diem.seproject.model.extensions.Color;

/**
 * Class representing generic closed shapes
 */
public abstract class BaseClosedShape extends BaseShape implements ClosedShape {

    protected Color fillColor;

    protected BaseClosedShape(Color strokeColor, Color fillColor) {
        super(strokeColor);
        this.fillColor = fillColor;
    }

    protected BaseClosedShape() {
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
