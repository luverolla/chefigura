package unisa.diem.seproject.model.tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;

import javafx.scene.input.MouseEvent;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.Tool;
import unisa.diem.seproject.model.tools.anchors.*;

import java.util.List;

/**
 * Tool to select a shape
 */
public class SelectionTool implements Tool {
    private final ShapeManager shapeManager;
    private Shape selected;
    private final Canvas canvas;
    final List<AnchorPoint> anchorPoints;

    public SelectionTool(ShapeManager sm, Canvas canvas) {
        this.shapeManager = sm;
        this.selected = null;
        this.canvas = canvas;
        this.anchorPoints = List.of(
                new CenterAnchorPoint(this, canvas)
        );
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            for (AnchorPoint anchorPoint : anchorPoints) {
                anchorPoint.mouseDragStart(event.getX(), event.getY());
            }
        });
        canvas.setOnMouseDragged(event -> {
            for (AnchorPoint anchorPoint : anchorPoints) {
                anchorPoint.mouseDragInProgress(event.getX(), event.getY());
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            for (AnchorPoint anchorPoint : anchorPoints) {
                anchorPoint.mouseDragEnd(event.getX(), event.getY());
            }
        });
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        this.selected = this.shapeManager.select(mouseX, mouseY);
        if(selected != null) {
            this.shapeManager.selectedShapeProperty.set(this.selected);
            this.shapeManager.redraw();
        }
        else {
            this.shapeManager.selectedShapeProperty.set(null);
            this.shapeManager.redraw();
        }
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        
    }

    @Override
    public void mouseMove(double mouseX, double mouseY) {
        canvas.getScene().setCursor(Cursor.DEFAULT);
        for (AnchorPoint anchorPoint: anchorPoints) {
            anchorPoint.mouseMove(mouseX, mouseY);
        }
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {
        
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

    public ShapeManager getShapeManager() {
        return this.shapeManager;
    }
}
