package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;
import unisa.diem.swproject.model.commands.*;

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

    public void redraw() {
        for (Shape shape : shapes)
            shape.draw(context);
    }

    public void draw(Tool t) {
        ShapeToolCommand command = new ShapeToolCommand(t);
        commandManager.execute(command);
        shapes.add(command.getShape());
    }

    public void draw(Shape s) {
        s.draw(context);
        shapes.add(s);
    }

    public void deleteShape(Shape s) {
        ShapeDeleteCommand command = new ShapeDeleteCommand(s, context);
        command.execute();
        shapes.remove(s);
    }

    public void copyShape(Shape s) {
        copiedShape = s;
    }

    public void pasteShape(boolean cut) {
        if (copiedShape != null) {
            Command command = new PasteShapeCommand(copiedShape, cut, this);
            command.execute();
        }
    }

    public void resetCopiedShape() {
        copiedShape = null;
    }

    public void groupShapes(Iterable<Shape> shapes)  {
        Command command = new GroupShapesCommand(shapes, this);
        command.execute();
    }

    public void ungroupShapes(ShapeGroup g)  {
        Command command = new GroupShapesCommand(g.getShapes(), this);
        command.rollback();
    }

    public void moveToFront(Shape s)  {
        int maxZIndex = shapes.stream().max(Comparator.comparing(Shape::getZIndex)).get().getZIndex();
        Command command = new SetZIndexCommand(this, s, maxZIndex + 1);
        command.execute();
    }

    public void moveToBack(Shape s)  {
        int minZIndex = shapes.stream().max(Comparator.comparing(Shape::getZIndex).reversed()).get().getZIndex();
        Command command = new SetZIndexCommand(this, s, minZIndex == 0 ? 0 : minZIndex - 1);
        command.execute();
    }

    public void moveForward(Shape s)  {
        Command command = new SetZIndexCommand(this, s, s.getZIndex() + 1);
        command.execute();
    }

    public void moveBackward(Shape s)  {
        Command command = new SetZIndexCommand(this, s, s.getZIndex() - 1);
        command.execute();
    }

    public Shape selectShape(double mouseX, double mouseY)  {
        return shapes.stream().filter(s -> s.contains(mouseX, mouseY)).findFirst().orElse(null);
    }

    public void setSelectedShape(Shape s)  {
       this.selectedShape = s;
    }

    public Shape getSelectedShape()  {
       return this.selectedShape;
    }

    public void importCustomShape(String libraryName)  {

    }

    public void exportCustomShape(CustomShape s, String libraryName)  {

    }
}