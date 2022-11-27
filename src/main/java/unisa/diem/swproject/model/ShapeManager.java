package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;
import unisa.diem.swproject.model.commands.*;

import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    private final List<Shape> shapes;
    private final GraphicsContext context;
    private final CommandManager commandManager;

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

    public int countShapes() {
        return shapes.size();
    }

    public boolean shapeIsPresent(Shape s) {
        return shapes.contains(s);
    }

    public void clear() {
        shapes.clear();
        redraw();
    }
}
