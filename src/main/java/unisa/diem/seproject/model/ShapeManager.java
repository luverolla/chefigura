package unisa.diem.seproject.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.commands.*;
import unisa.diem.seproject.model.extensions.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages shapes operations
 * Implements the Composite pattern
 */
public class ShapeManager implements Serializable {

    private final List<Shape> shapes;
    private transient GraphicsContext context;
    private final transient CommandManager commandManager;
    private Shape selectedShape;
    private Shape copiedShape;
    public transient ObjectProperty<Shape> selectedShapeProperty;
    public transient ObjectProperty<Shape> copiedShapeProperty;

    public ShapeManager(GraphicsContext context, CommandManager commandManager) {
        this.shapes = new ArrayList<>();
        this.commandManager = commandManager;
        this.context = context;
        this.selectedShapeProperty = new SimpleObjectProperty<>();
        this.copiedShapeProperty = new SimpleObjectProperty<>();
    }

    public ShapeManager(GraphicsContext context, CommandManager commandManager, List<Shape> shapes) {
        this.shapes = shapes;
        this.commandManager = commandManager;
        this.context = context;
        this.selectedShapeProperty = new SimpleObjectProperty<>();
        this.copiedShapeProperty = new SimpleObjectProperty<>();
    }

    public GraphicsContext getGraphicsContext() {
        return context;
    }

    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    public void draw(Shape s) {
        ShapeDrawCommand command = new ShapeDrawCommand(s, this);
        commandManager.execute(command);
    }

    public void redraw() {
        context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
        context.setFill(new Color(1, 1, 1).toFXColor());
        context.fillRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
        for (Shape s : shapes) {
            s.draw(context);
        }
    }

    public void add(Shape s) {
        shapes.add(s);
        if (context != null)
            s.draw(context);
    }

    public void remove(Shape s) {
        shapes.remove(s);
        if (context != null) {
            redraw();
        }
    }

    public void clear() {
        shapes.clear();
        if (context != null) {
            redraw();
        }
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public Shape select(double mouseX, double mouseY) {
        for (Shape s : shapes) {
            if (s.contains(mouseX, mouseY)) {
                return s;
            }
        }
        return null;
    }

    public Shape getSelectedShape() {
        return this.selectedShape;
    }

    public void setSelectedShape(Shape s) {
        this.selectedShape = s;
    }

    public void deleteShape(Shape s) {
        ShapeDeleteCommand command = new ShapeDeleteCommand(this, s);
        commandManager.execute(command);
        shapes.remove(s);
    }

    public void copyShape(Shape s) {
        this.copiedShape = s;
    }

    public void changeShapeColor(Shape shape, Color strokeColor, Color fillColor) {
        shape.setStrokeColor(strokeColor);
        if (shape instanceof ClosedShape) {
            ((ClosedShape) shape).setFillColor(fillColor);
        }
        redraw();
    }
}
