package unisa.diem.seproject.model;

import unisa.diem.seproject.model.extensions.Color;

/**
 * Generic closed shape
 */
public interface ClosedShape extends Shape {
    Color getFillColor();
    void setFillColor(Color fillColor);
}

