package unisa.diem.swproject.model;

public class PasteShapeCommand implements Command {
    private final Shape original;

    private Shape cloned;
    private final ShapeManager sm;

    public PasteShapeCommand(Shape original, ShapeManager sm) {
        this.sm = sm;
        this.original = original;
    }

    public int execute() {
        cloned = original.clone();
        sm.draw(cloned);

        return 0;
    }

    public int rollback() {
        sm.deleteShape(cloned);

        return 0;
    }
}
