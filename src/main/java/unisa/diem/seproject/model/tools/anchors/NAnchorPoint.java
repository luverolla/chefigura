package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.AnchorPoint;
import unisa.diem.seproject.model.tools.SelectionTool;

public class NAnchorPoint implements AnchorPoint {

    private final Canvas canvas;
    private final ShapeManager sm;
    private double oldY;
    private boolean isDragging;
    private Shape shadow;

    public NAnchorPoint(SelectionTool selectionTool, Canvas canvas) {
        this.canvas = canvas;
        this.sm = selectionTool.getShapeManager();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnNAnchorPoint(mouseX, mouseY, sm.getZoomFactor())) {
                canvas.getScene().setCursor(Cursor.N_RESIZE);
            }
        }
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnNAnchorPoint(mouseX, mouseY, sm.getZoomFactor())) {
                isDragging = true;
                oldY = sm.selectedShapeProperty.get().getBounds().getAnchorPoints()[0].getY();
                shadow = sm.selectedShapeProperty.get().copy();
                canvas.getScene().setCursor(Cursor.N_RESIZE);
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
            double deltaY = mouseY / sm.getZoomFactor() - oldY;
            if (deltaY > sm.selectedShapeProperty.get().getBounds().getHeight()) {
                return;
            }
            sm.stretchCommand(sm.selectedShapeProperty.get(), 0, deltaY,-1);
            isDragging = false;
        }
    }

    @Override
    public void mouseDragInProgress(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaY = mouseY / sm.getZoomFactor() - shadow.getBounds().getAnchorPoints()[0].getY();
            if (deltaY > sm.selectedShapeProperty.get().getBounds().getHeight()) {
                return;
            }
            sm.stretch(shadow, 0, deltaY,-1);
            sm.drawNotPersistent(shadow);
        }
    }
}
