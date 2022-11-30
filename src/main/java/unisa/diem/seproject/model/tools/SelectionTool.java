package unisa.diem.seproject.model.tools;

import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.Tool;

public class SelectionTool implements Tool {
    private ShapeManager shapeManager;
    private Shape selected;

    public SelectionTool(ShapeManager sm){
        this.shapeManager = sm;
        this.selected = null;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        this.selected = this.shapeManager.select(mouseX, mouseY);
    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {

    }

    @Override
    public void apply() {
        this.shapeManager.setSelectedShape(this.selected);
    }


}
