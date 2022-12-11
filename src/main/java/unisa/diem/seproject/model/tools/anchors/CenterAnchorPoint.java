package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.SelectionTool;
import unisa.diem.seproject.model.tools.AnchorPoint;

/**
 * Class that implements the center anchor point
 * <p>
 *     this point act as handle for moving shapes
 * </p>
 */
public class CenterAnchorPoint implements AnchorPoint {
    private final Canvas canvas;
    private final ShapeManager sm;
    private double oldX;
    private double oldY;
    private boolean isDragging;
    private Shape shadow;

    public CenterAnchorPoint(SelectionTool selectionTool, Canvas canvas) {
        this.canvas = canvas;
        this.sm = selectionTool.getShapeManager();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null) {
            if (sm.selectedShapeProperty.get().getBounds().mouseOnCenter(mouseX, mouseY, sm.getZoomFactor())) {
                canvas.getScene().setCursor(Cursor.OPEN_HAND);
            }
        }
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && sm.selectedShapeProperty.get().getBounds().mouseOnCenter(mouseX, mouseY, sm.getZoomFactor())) {
            isDragging = true;
            this.oldX = sm.selectedShapeProperty.get().getBounds().getCenter().getX();
            this.oldY = sm.selectedShapeProperty.get().getBounds().getCenter().getY();
            canvas.getScene().setCursor(Cursor.CLOSED_HAND);
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
            double deltaY = mouseY / sm.getZoomFactor() - oldY;
            sm.moveCommand(sm.selectedShapeProperty.get(), deltaX, deltaY);
        }
        isDragging = false;
    }

    @Override
    public void mouseDragInProgress(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaX = mouseX / sm.getZoomFactor() - shadow.getBounds().getCenter().getX();
            double deltaY = mouseY / sm.getZoomFactor() - shadow.getBounds().getCenter().getY();
            sm.move(shadow, deltaX, deltaY);
            sm.drawNotPersistent(shadow);
        }
    }
}
