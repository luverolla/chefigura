package unisa.diem.swproject.model;

public class SetZIndexCommand extends BaseCommand {
    private final ShapeManager shapeManager;
    private final Shape shape;

    private final int oldZIndex;
    private final int newZIndex;

    public SetZIndexCommand(ShapeManager shapeManager, Shape shape, int newZIndex) {
        this.shapeManager = shapeManager;
        this.shape = shape;
        this.oldZIndex = shape.getZIndex();
        this.newZIndex = newZIndex;
    }

    @Override
    public int execute() {
        shape.setZIndex(shapeManager.getGraphicsContext(), newZIndex);
        return 0;
    }

    @Override
    public int rollback() {
        shape.setZIndex(shapeManager.getGraphicsContext(), oldZIndex);
        return 0;
    }
}
