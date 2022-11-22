package unisa.diem.swproject.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Sheet extends Canvas {
    private String name;
    private SheetFormat format;
    private double zoomLevel;
    private Tool currentTool;
    private ShapeManager shapeManager;

    public Sheet(String name, SheetFormat format, GraphicsContext gc, CommandManager cm) {
        this.name = name;
        this.format = format;
        this.zoomLevel = 1;
        this.currentTool = null;
        this.shapeManager = new ShapeManager(gc, cm);
    }
    public void drawGrid(double size) {

    }

    public void zoom(double factor) {
        this.zoomLevel *= factor;
        this.shapeManager.redraw();
    }
}
