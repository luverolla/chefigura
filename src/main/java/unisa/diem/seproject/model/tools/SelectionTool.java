package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.Tool;

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
        }
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {

    }

    @Override
    public void apply() {
        this.shapeManager.setSelectedShape(this.selected);
    }


}
