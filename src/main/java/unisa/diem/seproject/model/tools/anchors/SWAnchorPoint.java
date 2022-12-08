package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.AnchorPoint;
import unisa.diem.seproject.model.tools.SelectionTool;

public class SWAnchorPoint implements AnchorPoint {

    private final Canvas canvas;
    private final ShapeManager sm;
    private double oldX;
    private double oldWidth;
    private boolean isDragging;
    private Shape shadow;

    public SWAnchorPoint(SelectionTool selectionTool, Canvas canvas) {
        this.canvas = canvas;
        this.sm = selectionTool.getShapeManager();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnSWAnchorPoint(mouseX, mouseY, sm.getZoomFactor())) {
                canvas.getScene().setCursor(Cursor.SW_RESIZE);
            }
        }
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && sm.selectedShapeProperty.get().getBounds().mouseOnSWAnchorPoint(mouseX, mouseY, sm.getZoomFactor())) {
            isDragging = true;
            this.oldX = sm.selectedShapeProperty.get().getBounds().getAnchorPoints()[3].getX();
            this.oldWidth = sm.selectedShapeProperty.get().getBounds().getWidth();
            canvas.getScene().setCursor(Cursor.SW_RESIZE);
            shadow = sm.selectedShapeProperty.get().copy();
            shadow.setAngle(sm.selectedShapeProperty.get().getAngle());
            shadow.setStrokeColor(shadow.getStrokeColor().fade(0.5));
            if (shadow instanceof ClosedShape)
                ((ClosedShape) shadow).setFillColor(((ClosedShape) shadow).getFillColor().fade(0.5));
        }
    }

    @Override
    public void mouseDragEnd(double mouseX, double mouseY) {
        sm.redraw();
        canvas.getScene().setCursor(Cursor.DEFAULT);
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaX = mouseX / sm.getZoomFactor() - oldX;
            if (deltaX > oldWidth / 2 - 5) {
                return;
            }
            sm.resizeCommand(sm.selectedShapeProperty.get(), -deltaX * 2);
        }
        isDragging = false;
    }

    @Override
    public void mouseDragInProgress(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaX = mouseX / sm.getZoomFactor() - shadow.getBounds().getAnchorPoints()[3].getX();
            if (deltaX > oldWidth / 2 - 5) {
                return;
            }
            sm.resize(shadow, -deltaX);
            sm.drawNotPersistent(shadow);
        }
    }
}
