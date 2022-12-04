package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.AnchorPoint;
import unisa.diem.seproject.model.tools.SelectionTool;

public class NWAnchorPoint implements AnchorPoint {

    private final Canvas canvas;
    private final ShapeManager sm;
    private double oldX;
    private double oldWidth;
    private boolean isDragging;
    private Shape shadow;

    public NWAnchorPoint(SelectionTool selectionTool, Canvas canvas) {
        this.canvas = canvas;
        this.sm = selectionTool.getShapeManager();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
       if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnNWAnchorPoint(mouseX, mouseY)) {
                canvas.getScene().setCursor(Cursor.NW_RESIZE);
            }
        }
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && sm.selectedShapeProperty.get().getBounds().mouseOnNWAnchorPoint(mouseX, mouseY)) {
            isDragging = true;
            this.oldX = sm.selectedShapeProperty.get().getBounds().getAnchorPoints()[0].getX();
            this.oldWidth = sm.selectedShapeProperty.get().getBounds().getWidth();
            canvas.getScene().setCursor(Cursor.NW_RESIZE);
            shadow = sm.selectedShapeProperty.get().copy();
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
            double deltaX = mouseX - oldX;
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
            double deltaX = mouseX - shadow.getBounds().getAnchorPoints()[0].getX();
            if (deltaX > oldWidth / 2 - 5) {
                return;
            }
            sm.resize(shadow, -deltaX);
            shadow.draw(canvas.getGraphicsContext2D());
        }
    }
}
