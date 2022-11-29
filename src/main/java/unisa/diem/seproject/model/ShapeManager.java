package unisa.diem.seproject.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import unisa.diem.seproject.model.commands.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShapeManager implements Serializable {

    private final List<Shape> shapes;
    private transient GraphicsContext context;
    private final transient CommandManager commandManager;

    public ShapeManager(GraphicsContext context, CommandManager commandManager) {
        this.shapes = new ArrayList<>();
        this.commandManager = commandManager;
        this.context = context;
    }

    public ShapeManager(GraphicsContext context, CommandManager commandManager, List<Shape> shapes) {
        this.shapes = shapes;
        this.commandManager = commandManager;
        this.context = context;
    }

    public GraphicsContext getGraphicsContext() {
        return context;
    }

    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    public void add(Shape s) {
        shapes.add(s);
        if(context != null)
            s.draw(context);
    }

    public void redraw() {
       context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
       context.setFill(Color.WHITE);
       context.fillRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
        for (Shape s : shapes) {
            s.draw(context);
        }
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

    public List<Shape> getShapes() {
        return shapes;
    }
}
