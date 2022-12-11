package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.extensions.Color;

/**
 * Generic tool for drawing generic closed shapes
 */
public interface ClosedShapeTool extends ShapeTool {
    void setFillColor(Color fillColor);
}
