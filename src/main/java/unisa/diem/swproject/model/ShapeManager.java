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

    public void add(Shape s) {
        shapes.add(s);
        s.draw(context);
    }

    public void remove(Shape s) {
        shapes.remove(s);
        redraw();
    }

    public void redraw() {
        Sheet area = (Sheet) context.getCanvas();
        area.buildDrawingArea();
        for (Shape shape : shapes)
            shape.draw(context);
    }

    public void draw(Shape s) {
        ShapeDrawCommand command = new ShapeDrawCommand(s, this);
        commandManager.execute(command);
    }

    public void deleteShape(Shape s) {
        ShapeDeleteCommand command = new ShapeDeleteCommand(s, context);
        command.execute();
        shapes.remove(s);
    }

    public void copyShape(Shape s) {
        copiedShape = s;
    }

    public void pasteShape(boolean cut) throws CloneNotSupportedException {
        if (copiedShape != null) {
            Command command = new PasteShapeCommand(copiedShape, cut, this);
            command.execute();
        }
    }

    public void resetCopiedShape() {
        copiedShape = null;
    }

    public void groupShapes(Iterable<Shape> shapes) throws CloneNotSupportedException {
        Command command = new GroupShapesCommand(shapes, this);
        command.execute();
    }

    public void ungroupShapes(ShapeGroup g)  {
        Command command = new GroupShapesCommand(g.getShapes(), this);
        command.rollback();
    }

    public void moveToFront(Shape s) throws CloneNotSupportedException {
        int maxZIndex = shapes.stream().max(Comparator.comparing(Shape::getZIndex)).get().getZIndex();
        Command command = new SetZIndexCommand(this, s, maxZIndex + 1);
        command.execute();
    }

    public void moveToBack(Shape s) throws CloneNotSupportedException {
        int minZIndex = shapes.stream().max(Comparator.comparing(Shape::getZIndex).reversed()).get().getZIndex();
        Command command = new SetZIndexCommand(this, s, minZIndex == 0 ? 0 : minZIndex - 1);
        command.execute();
    }

    public void moveForward(Shape s) throws CloneNotSupportedException {
        Command command = new SetZIndexCommand(this, s, s.getZIndex() + 1);
        command.execute();
    }

    public void moveBackward(Shape s) throws CloneNotSupportedException {
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

    public int countShapes() {
        return shapes.size();
    }

    public boolean shapeIsPresent(Shape s) {
        return shapes.contains(s);
    }
}