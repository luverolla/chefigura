package unisa.diem.swproject.model;

public class ShapeToolCommand implements Command {
    private final Tool tool;

    private Shape shape;

    public ShapeToolCommand(Tool tool) {
        this.tool = tool;
        this.shape = null;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public int execute() {
        tool.apply();
        shape = tool.getShape();
        return 0;
    }

    @Override
    public int rollback() {
        tool.revert();
        shape = tool.getShape();
        return 0;
    }
}
