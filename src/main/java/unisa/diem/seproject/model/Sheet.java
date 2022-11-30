package unisa.diem.seproject.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import unisa.diem.seproject.Converter;

import java.io.Serial;
import java.io.Serializable;

public class Sheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    static final double DRAW_AREA_HEIGHT = 400;
    static final double DRAW_AREA_WIDTH = 400;
    private final SheetFormat format;
    private transient Tool currentTool;
    private final ShapeManager shapeManager;

    public Sheet(SheetFormat format, CommandManager cm) {
        this.format = format;
        this.currentTool = null;
        this.shapeManager = new ShapeManager(null, cm);
    }

    public Sheet(Sheet s, CommandManager cm) {
        this.format = s.getFormat();
        this.currentTool = null;
        this.shapeManager = new ShapeManager(null, cm, s.shapeManager().getShapes());
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

    public void buildDrawingArea(Canvas canvas) {
        double DPI = Screen.getPrimary().getDpi();
        double height = Converter.toPixels(DRAW_AREA_HEIGHT, DPI),
                width = Converter.toPixels(DRAW_AREA_WIDTH, DPI);
        double sheetHeight = Converter.toPixels(format.getHeight(), DPI),
                sheetWidth = Converter.toPixels(format.getWidth(), DPI);
        canvas.setWidth(width);
        canvas.setHeight(height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        this.shapeManager.setContext(context);
        context.clearRect(0, 0, width, height);
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, width, height);
        if(format != SheetFormat.NONE) {
            double padX = Converter.toPixels((DRAW_AREA_WIDTH - getFormat().getWidth()) / 2, DPI);
            double padY = Converter.toPixels((DRAW_AREA_HEIGHT - getFormat().getHeight()) / 2, DPI);
            context.strokeRect(padX, padY, sheetWidth, sheetHeight);
        }
    }
}
