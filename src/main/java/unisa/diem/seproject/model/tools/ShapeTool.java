package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.Tool;

/**
 * Generic tool for drawing generic shapes
 */
public interface ShapeTool extends Tool {
    void setStrokeColor(Color strokeColor);
}
