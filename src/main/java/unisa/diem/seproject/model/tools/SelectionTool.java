package unisa.diem.seproject.model.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.Tool;

/**
 * Tool to select a shape
 */
public class SelectionTool implements Tool {
    private final ShapeManager shapeManager;
    private Shape selected;
    private boolean mouseIsDown;
    private final Canvas canvas;

    public SelectionTool(ShapeManager sm, Canvas canvas) {
        this.shapeManager = sm;
        this.selected = null;
        this.mouseIsDown = false;
        this.canvas = canvas;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        mouseIsDown = true;
        shapeManager.redraw();
        this.selected = this.shapeManager.select(mouseX, mouseY);

        if (selected != null) {
            this.shapeManager.selectedShapeProperty.set(this.selected);
            selected.getBounds().show(shapeManager.getGraphicsContext());
        }
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if (mouseIsDown && selected != null) {
            selected.move(mouseX, mouseY);
        } else {
            if (selected != null) {
                if (selected.getBounds().mouseOnCenter(mouseX, mouseY)) {
                    canvas.getScene().setCursor(Cursor.OPEN_HAND);
                } else if (selected.getBounds().mouseOnNWAnchorPoint(mouseX, mouseY)) {
                    canvas.getScene().setCursor(Cursor.NW_RESIZE);
                } else if (selected.getBounds().mouseOnNEAnchorPoint(mouseX, mouseY)) {
                    canvas.getScene().setCursor(Cursor.NE_RESIZE);
                } else if (selected.getBounds().mouseOnSEAnchorPoint(mouseX, mouseY)) {
                    canvas.getScene().setCursor(Cursor.SE_RESIZE);
                } else if (selected.getBounds().mouseOnSWAnchorPoint(mouseX, mouseY)) {
                    canvas.getScene().setCursor(Cursor.SW_RESIZE);
                } else {
                    canvas.getScene().setCursor(Cursor.DEFAULT);
                }
            }
        }
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {
        mouseIsDown = false;
    }

    @Override
    public void apply() {
        this.shapeManager.setSelectedShape(this.selected);
    }

    @Override
    public void reset() {
        this.selected = null;
        shapeManager.redraw();
    }
}
