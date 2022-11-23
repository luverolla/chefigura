package unisa.diem.swproject.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import unisa.diem.swproject.Converter;

public class Sheet extends Canvas {

    static final double DRAW_AREA_HEIGHT = 400;
    static final double DRAW_AREA_WIDTH = 400;

    private String name;
    private SheetFormat format;
    private double zoomLevel;
    private Tool currentTool;
    private final ShapeManager shapeManager;

    private final GraphicsContext gc;

    private Project project;

    public Sheet(String name, SheetFormat format, CommandManager cm) {
        this.gc = getGraphicsContext2D();
        this.name = name;
        this.format = format;
        this.zoomLevel = 1;
        this.currentTool = null;
        this.shapeManager = new ShapeManager(this.getGraphicsContext2D(), cm);
    }
    public void drawGrid(double size) {

    }

    public ShapeManager shapeManager() {
        return shapeManager;
    }

    public void zoom(double factor) {
        this.zoomLevel *= factor;
        this.shapeManager.redraw();
    }

    public Project getProject() {
        return project;
    }

    public SheetFormat getFormat() {
        return format;
    }

    public void buildDrawingArea() {
        double DPI = Screen.getPrimary().getDpi();
        double height = Converter.toPixels(DRAW_AREA_HEIGHT, DPI),
                width = Converter.toPixels(DRAW_AREA_WIDTH, DPI);
        double sheetHeight = Converter.toPixels(format.getHeight(), DPI),
                sheetWidth = Converter.toPixels(format.getWidth(), DPI);

        setHeight(height);
        setWidth(width);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        double padX = Converter.toPixels((DRAW_AREA_WIDTH - getFormat().getWidth()) / 2, DPI);
        double padY = Converter.toPixels((DRAW_AREA_HEIGHT - getFormat().getHeight()) / 2, DPI);
        gc.strokeRect(padX, padY, sheetWidth, sheetHeight);
    }
}
