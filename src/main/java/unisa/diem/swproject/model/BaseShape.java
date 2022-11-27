package unisa.diem.swproject.model;

import javafx.scene.paint.Color;

public abstract class BaseShape implements Shape {
    protected Color strokeColor;

    protected BaseShape(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    protected BaseShape() {
        this.strokeColor = Color.BLACK;
    }
}
