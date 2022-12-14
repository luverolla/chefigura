package unisa.diem.seproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;

import unisa.diem.seproject.model.commands.*;
import unisa.diem.seproject.model.extensions.Color;

/**
 * Class that manages shapes operations
 * <p>
 * Implements the "Composite" pattern
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

    /**
     * Draw a shape that is not meant to be in the collection
     * @param s the shape to draw
     */
    public void drawNotPersistent(Shape s) {
        s.draw(context, zoomFactor);
    }

    /**
     * Clears the canvas and draw all shapes in the collection
     */
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

    public void resize(Shape shape, double resizeFactor) {
        shape.resize(resizeFactor, zoomFactor);
        if (context != null)
            redraw();
    }

    public void resizeCommand(Shape shape, double resizeFactor) {
        Command command = new ShapeResizeCommand(this, shape, resizeFactor);
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

    public void stretch(Shape shape, double deltaX, double deltaY, int direction) {
        shape.stretch(deltaX, deltaY, direction);
        if (context != null)
            redraw();
    }

    public void stretchCommand(Shape shape, double deltaX, double deltaY, int direction) {
        Command command = new ShapeStretchCommand(this, shape, deltaX, deltaY, direction);
        commandManager.execute(command);
    }

    /**
     * Call the mirror method for the given shape
     * @param s the given shape
     * @param horizontal true if the mirror is on the x-axis, false if it is on the y-axis
     */
    public void mirror(Shape s, boolean horizontal) {
        if (horizontal) {
            s.mirrorHorizontal();
        } else {
            s.mirrorVertical();
        }
        if (context != null)
            redraw();
    }

    public void mirrorCommand(Shape s, boolean horizontal) {
        Command command = new ShapeMirroringCommand(this, s, horizontal);
        commandManager.execute(command);
    }
}
