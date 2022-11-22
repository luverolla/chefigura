package unisa.diem.swproject.model;

public class GroupShapesCommand extends BaseCommand {
    private final ShapeManager shapeManager;

    private final ShapeGroup group;

    public GroupShapesCommand(Iterable<Shape> shapes, ShapeManager shapeManager) {
        this.shapeManager = shapeManager;
        this.group = new ShapeGroup(shapes);
    }

    @Override
    public int execute() {
        for(Shape s : group.getShapes())
            shapeManager.deleteShape(s);
        shapeManager.draw(group);
        return 0;
    }

    @Override
    public int rollback() {
        Iterable<Shape> shapes = group.getShapes();
        shapeManager.deleteShape(group);
        for(Shape s : shapes)
            shapeManager.draw(s);
        return 0;
    }
}
