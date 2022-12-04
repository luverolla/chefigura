package unisa.diem.seproject.model.tools.anchors;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.ClosedShape;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.SelectionTool;
import unisa.diem.seproject.model.tools.AnchorPoint;

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
            if (sm.selectedShapeProperty.get().getBounds().mouseOnCenter(mouseX, mouseY)) {
                canvas.getScene().setCursor(Cursor.OPEN_HAND);
            }
        }
    }

    @Override
    public void mouseDragStart(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && sm.selectedShapeProperty.get().getBounds().mouseOnCenter(mouseX, mouseY)) {
            isDragging = true;
            this.oldX = sm.selectedShapeProperty.get().getBounds().getCenter().getX();
            this.oldY = sm.selectedShapeProperty.get().getBounds().getCenter().getY();
            canvas.getScene().setCursor(Cursor.CLOSED_HAND);
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
            double deltaY = mouseY - oldY;
            sm.moveCommand(sm.selectedShapeProperty.get(), deltaX, deltaY);
        }
        isDragging = false;
    }

    @Override
    public void mouseDragInProgress(double mouseX, double mouseY) {
        if (sm.selectedShapeProperty.get() != null && isDragging) {
            double deltaX = mouseX - shadow.getBounds().getCenter().getX();
            double deltaY = mouseY - shadow.getBounds().getCenter().getY();
            sm.move(shadow, deltaX, deltaY);
            shadow.draw(canvas.getGraphicsContext2D());
        }
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public void setX(double x) {

    }

    @Override
    public void setY(double y) {

    }
}
