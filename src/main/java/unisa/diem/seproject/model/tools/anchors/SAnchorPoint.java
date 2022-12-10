package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.AnchorPoint;
import unisa.diem.seproject.model.tools.SelectionTool;

public class SAnchorPoint implements AnchorPoint {

    private final Canvas canvas;
    private final ShapeManager sm;
    private double oldX;
    private double oldWidth;
    private boolean isDragging;
    private Shape shadow;

    public SAnchorPoint(SelectionTool selectionTool, Canvas canvas) {
        this.canvas = canvas;
        this.sm = selectionTool.getShapeManager();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
    }

    @Override
    public void mouseDragEnd(double mouseX, double mouseY) {
    }

    @Override
    public void mouseDragInProgress(double mouseX, double mouseY) {
    }
}
