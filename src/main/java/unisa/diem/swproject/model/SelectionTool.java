package unisa.diem.swproject.model;

public class SelectionTool implements Tool {
    private final ShapeManager shapeManager;
    private Shape selected;

    public SelectionTool(ShapeManager sm){
        this.shapeManager = sm;
        this.selected = null;
    }

    @Override
    public void mouseDown(double mouseX, double mouseY) {
        this.selected = this.shapeManager.selectShape(mouseX, mouseY);
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
    public Shape getShape() {
        return this.selected;
    }
}
