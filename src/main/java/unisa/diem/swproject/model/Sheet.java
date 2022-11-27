package unisa.diem.swproject.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import unisa.diem.swproject.Converter;

public class Sheet extends Canvas {

    static final double DRAW_AREA_HEIGHT = 400;
    static final double DRAW_AREA_WIDTH = 400;
    private final SheetFormat format;
    private Tool currentTool;
    private final ShapeManager shapeManager;
    private final GraphicsContext gc;

    public Sheet(SheetFormat format, CommandManager cm) {
        this.gc = getGraphicsContext2D();
        this.format = format;
        this.currentTool = null;
        this.shapeManager = new ShapeManager(this.getGraphicsContext2D(), cm);
    }

    public ShapeManager shapeManager() {
        return shapeManager;
    }

    public void setCurrentTool(Tool t) {
        this.currentTool = t;
    }

    public Tool getCurrentTool() {
        return currentTool;
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

        gc.clearRect(0, 0, width, height);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        if(format != SheetFormat.NONE) {
            double padX = Converter.toPixels((DRAW_AREA_WIDTH - getFormat().getWidth()) / 2, DPI);
            double padY = Converter.toPixels((DRAW_AREA_HEIGHT - getFormat().getHeight()) / 2, DPI);
            gc.strokeRect(padX, padY, sheetWidth, sheetHeight);
        }
    }
}
