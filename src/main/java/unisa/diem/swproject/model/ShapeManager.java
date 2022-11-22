package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapeManager {
    private final List<Shape> shapes;
    private final GraphicsContext context;

    private final CommandManager commandManager;
    private Shape copiedShape;
    private Shape selectedShape;

    public ShapeManager(GraphicsContext context, CommandManager commandManager) {
        this.shapes = new ArrayList<>();
        this.commandManager = commandManager;
        this.context = context;
    }

    public GraphicsContext getGraphicsContext() {
        return context;
    }

    public int redraw() {
        for (Shape shape : shapes) {
            shape.draw(context);
        }
        return 0;
    }

    int draw(Tool t) {
        ShapeToolCommand command = new ShapeToolCommand(t);
        commandManager.execute(command);
        shapes.add(command.getShape());
        return 0;
    }

    int draw(Shape s) {
        s.draw(context);
        shapes.add(s);
        return 0;
    }

    int deleteShape(Shape s) {
        ShapeDeleteCommand command = new ShapeDeleteCommand(s, context);
        command.execute();
        shapes.remove(s);
        return 0;
    }

    void copyShape(Shape s) {
        copiedShape = s;
    }

    void pasteShape(boolean cut) {
        if (copiedShape != null) {
            Command command = new PasteShapeCommand(copiedShape, cut, this);
            command.execute();
        }
    }

    void resetCopiedShape() {
        copiedShape = null;
    }

    void groupShapes(Iterable<Shape> shapes) {
        Command command = new GroupShapesCommand(shapes, this);
        command.execute();
    }

    void ungroupShapes(ShapeGroup g) {
        Command command = new GroupShapesCommand(g.getShapes(), this);
        command.rollback();
    }

    void moveToFront(Shape s) {
        int maxZIndex = shapes.stream().max(Comparator.comparing(Shape::getZIndex)).get().getZIndex();
        Command command = new SetZIndexCommand(this, s, maxZIndex + 1);
        command.execute();
    }

    void moveToBack(Shape s) {
        int minZIndex = shapes.stream().max(Comparator.comparing(Shape::getZIndex).reversed()).get().getZIndex();
        Command command = new SetZIndexCommand(this, s, minZIndex == 0 ? 0 : minZIndex - 1);
        command.execute();
    }

    void moveForward(Shape s) {
        Command command = new SetZIndexCommand(this, s, s.getZIndex() + 1);
        command.execute();
    }

    void moveBackward(Shape s) {
        Command command = new SetZIndexCommand(this, s, s.getZIndex() - 1);
        command.execute();
    }

    Shape selectShape(double mouseX, double mouseY) {
        return shapes.stream().filter(s -> s.contains(mouseX, mouseY)).findFirst().orElse(null);
    }

    void setSelectedShape(Shape s) {
       this.selectedShape = s;
    }

    Shape getSelectedShape() {
       return this.selectedShape;
    }

    void importCustomShape(String libraryName) {

    }

    void exportCustomShape(CustomShape s, String libraryName) {

    }
}