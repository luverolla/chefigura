package unisa.diem.seproject.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.commands.*;
import unisa.diem.seproject.model.extensions.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that manages shapes operations
 * <p>
 * Implements the Composite pattern
 * </p>
 */
public class ShapeManager implements Serializable {

    private final List<Shape> shapes;
    private transient GraphicsContext context;
    private final transient CommandManager commandManager;
    public final transient ObjectProperty<Shape> selectedShapeProperty;
    public final transient ObjectProperty<Shape> copiedShapeProperty;
    private transient double zoomFactor = 1;

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

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public GraphicsContext getGraphicsContext() {
        return context;
    }

    public void setContext(GraphicsContext context) {
        this.context = context;
    }

    public Shape getShape(Shape shape){
        return shapes.contains(shape) ? shape : null;
    }

    public boolean isEmpty(){
        return shapes.isEmpty();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void drawNotPersistent(Shape s) {
        s.draw(context, zoomFactor);
    }

    public void redraw() {
        if (context != null) {
            context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
            context.setFill(new Color(1, 1, 1).toFXColor());
            context.fillRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
            for (Shape s : shapes) {
                s.draw(context, zoomFactor);
            }
            if (selectedShapeProperty.get() != null)
                selectedShapeProperty.get().getBounds().show(context, zoomFactor);
        }
    }

    public Shape select(double mouseX, double mouseY) {
        for (Shape s : shapes) {
            if (s.contains(mouseX, mouseY, zoomFactor)) {
                return s;
            }
        }
        return null;
    }

    public void add(Shape s) {
        shapes.add(s);
        if (context != null)
            s.draw(context, zoomFactor);
    }

    public void remove(Shape s) {
        if(selectedShapeProperty.get() == s)
            selectedShapeProperty.set(null);
        shapes.remove(s);
        if (context != null)
            redraw();
    }

    public void clear() {
        selectedShapeProperty.set(null);
        shapes.clear();
        if (context != null) {
            redraw();
        }
    }

    public void drawCommand(Shape s) {
        Command command = new ShapeDrawCommand(s, this);
        commandManager.execute(command);
    }

    public void copyShape(Shape s) {
        this.copiedShapeProperty.set(s);
    }

    public void changeShapeColor(Shape shape, Color strokeColor, Color fillColor) {
        shape.setStrokeColor(strokeColor);
        if (shape instanceof ClosedShape) {
            ((ClosedShape) shape).setFillColor(fillColor);
        }
        redraw();
    }

    public void move(Shape shape, double deltaX, double deltaY) {
        if (context != null)
            redraw();
        shape.move(deltaX, deltaY, zoomFactor);
    }

    public void moveCommand(Shape shape, double deltaX, double deltaY) {
        Command command = new ShapeMoveCommand(this, shape, deltaX, deltaY);
        commandManager.execute(command);
    }

    public void resize(Shape shape, double delta) {
        if (context != null)
            redraw();
        shape.resize(delta, zoomFactor);
    }

    public void resizeCommand(Shape shape, double delta) {
        Command command = new ShapeResizeCommand(this, shape, delta);
        commandManager.execute(command);
    }
    public void moveToFront(Shape shape){
        int maxZIndex = Collections.max(shapes).getZIndex();
        shape.setZIndex(maxZIndex + 1);
        Collections.sort(shapes);
        redraw();
    }
    public void moveToFrontCommand(Shape shape){
        Command command = new ShapeMoveToFrontCommand(this, shape);
        commandManager.execute(command);
    }
    public void moveToBack(Shape shape){
        int minZIndex = Collections.min(shapes).getZIndex();
        shape.setZIndex(minZIndex - 1);
        Collections.sort(shapes);
        redraw();
    }
    public void moveToBackCommand(Shape shape){
        Command command = new ShapeMoveToBackCommand(this, shape);
        commandManager.execute(command);
    }

    public void rotate(Shape s, double angle) {
        s.rotate(angle);
        if (context != null)
            redraw();
    }

    public void rotateCommand(Shape s, double angle) {
        Command command = new ShapeRotationCommand(this, s, angle);
        commandManager.execute(command);
    }
}
