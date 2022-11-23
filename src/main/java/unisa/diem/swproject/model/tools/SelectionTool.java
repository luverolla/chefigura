package unisa.diem.swproject.model.tools;

import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.Tool;

public class SelectionTool implements Tool {
    private final ShapeManager shapeManager;
    private Shape selected;

    private String hint;

    public SelectionTool(ShapeManager sm) {
        this.shapeManager = sm;
        this.selected = null;
        this.hint = "Select a shape";
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        this.selected = this.shapeManager.selectShape(mouseX, mouseY);
        hint = this.selected == null ? "Select a shape" : "Shape selected";
    }

    @Override
    public void mouseUp(double mouseX, double mouseY) {

    }

    @Override
    public void mouseDrag(double mouseX, double mouseY) {

    }

    @Override
    public boolean isShapeTool() {
        return true;
    }

    @Override
    public int apply() {
        this.shapeManager.setSelectedShape(this.selected);
        return 0;
    }

    @Override
    public int revert() {
        this.shapeManager.setSelectedShape(null);
        return 0;
    }

    @Override
    public String getCurrentHint() {
        return hint;
    }

    @Override
    public Shape getShape() {
        return this.selected;
    }
}
