package unisa.diem.seproject.model;

import unisa.diem.seproject.model.extensions.Color;

public abstract class BaseShape implements Shape {
    protected Color strokeColor;

    protected BaseShape(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    protected BaseShape() {
        this.strokeColor = Color.BLACK;
    }
}
