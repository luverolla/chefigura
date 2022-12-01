package unisa.diem.seproject.model.tools;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.Tool;
import unisa.diem.seproject.model.extensions.Point;

public class SelectionTool implements Tool {
    private final ShapeManager shapeManager;
    private Shape selected;
    private boolean mouseIsDown;

    public SelectionTool(ShapeManager sm){
        this.shapeManager = sm;
        this.selected = null;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        mouseIsDown = true;
        shapeManager.redraw();
        this.selected = this.shapeManager.select(mouseX, mouseY);
        if (selected != null) {
            selected.getBounds().show(shapeManager.getGraphicsContext());
        }
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {
        if (mouseIsDown && selected != null) {
            selected.move(mouseX, mouseY);
        } /*else {
            if (selected != null) {
                if (selected.getBounds().mouseOnCenter(mouseX, mouseY)) {

                } else {
                    if (selected.getBounds().mouseOnNEAnchorPoint(mouseX, mouseY)) {

                    } else {
                        if (selected.getBounds().mouseOnNWAnchorPoint(mouseX, mouseY)) {

                        } else {
                            if (selected.getBounds().mouseOnSWAnchorPoint(mouseX, mouseY)) {

                            } else {
                                if (selected.getBounds().mouseOnSEAnchorPoint(mouseX, mouseY)) {

                                } else {

                                }
                            }
                        }
                    }
                }
            }
        }*/
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


}
