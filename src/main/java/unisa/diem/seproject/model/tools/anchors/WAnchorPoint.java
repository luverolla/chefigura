package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.AnchorPoint;
import unisa.diem.seproject.model.tools.SelectionTool;

public class WAnchorPoint implements AnchorPoint {

    private final Canvas canvas;
    private final ShapeManager sm;
    private double oldX;
    private boolean isDragging;
    private Shape shadow;

    public WAnchorPoint(SelectionTool selectionTool, Canvas canvas) {
        this.canvas = canvas;
        this.sm = selectionTool.getShapeManager();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnWAnchorPoint(mouseX, mouseY, sm.getZoomFactor())) {
                canvas.getScene().setCursor(Cursor.W_RESIZE);
            }
        }
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnWAnchorPoint(mouseX, mouseY, sm.getZoomFactor())) {
                isDragging = true;
                oldX = sm.selectedShapeProperty.get().getBounds().getAnchorPoints()[3].getX();
                shadow = sm.selectedShapeProperty.get().copy();
                canvas.getScene().setCursor(Cursor.W_RESIZE);
                shadow.setStrokeColor(shadow.getStrokeColor().fade(0.5));
                if (shadow instanceof ClosedShape)
                    ((ClosedShape) shadow).setFillColor(((ClosedShape) shadow).getFillColor().fade(0.5));
            }
        }
    }

    @Override
    public void mouseDragEnd(double mouseX, double mouseY) {
        sm.redraw();
        canvas.getScene().setCursor(Cursor.DEFAULT);
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaX = mouseX / sm.getZoomFactor() - oldX;
            if (deltaX > sm.selectedShapeProperty.get().getBounds().getWidth()) {
                return;
            }
            sm.stretchCommand(sm.selectedShapeProperty.get(), deltaX,  0, -1);
        }
        isDragging = false;
    }

    @Override
    public void mouseDragInProgress(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaX = mouseX / sm.getZoomFactor() - shadow.getBounds().getAnchorPoints()[3].getX();
            if (deltaX > sm.selectedShapeProperty.get().getBounds().getWidth()) {
                return;
            }
            sm.stretch(shadow, deltaX, 0, -1);
            sm.drawNotPersistent(shadow);
        }
    }
}
