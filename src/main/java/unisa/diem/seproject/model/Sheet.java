package unisa.diem.seproject.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

import unisa.diem.seproject.Converter;
import unisa.diem.seproject.model.extensions.Color;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class that represents a sheet of a project
 *<p>
 * Each sheet has its own canvas
 * </p>
 */
public class Sheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    static final double DRAW_AREA_HEIGHT = 200;
    static final double DRAW_AREA_WIDTH = 200;
    private static final double DEFAULT_GRID_SIZE = 10;
    private static final double DEFAULT_GRID_OPACITY = 0.15;
    private final SheetFormat format;
    private transient Tool currentTool;
    private final ShapeManager shapeManager;
    public final transient DoubleProperty gridSizeProperty;

    private transient Canvas gridCanvas;

    public Sheet(SheetFormat format, CommandManager cm) {
        this.format = format;
        this.currentTool = null;
        this.shapeManager = new ShapeManager(null, cm);
        this.gridSizeProperty = new SimpleDoubleProperty(DEFAULT_GRID_SIZE);
        this.gridSizeProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 0) {
                this.gridSizeProperty.setValue(0);
            }
            buildGrid(gridCanvas, 1);
        });
    }

    public Sheet(Sheet s, CommandManager cm) {
        this.format = s.getFormat();
        this.currentTool = null;
        this.shapeManager = new ShapeManager(null, cm, s.shapeManager().getShapes());
        this.gridSizeProperty = new SimpleDoubleProperty(DEFAULT_GRID_SIZE);
        this.gridSizeProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 0) {
                this.gridSizeProperty.setValue(0);
            }
            buildGrid(gridCanvas, 1);
        });
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

    public void build(Pane container, Canvas sheetCanvas, Canvas gridCanvas, double zoomFactor) {
        container.getChildren().clear();
        container.getChildren().addAll(sheetCanvas, gridCanvas);
        buildDrawingArea(sheetCanvas, zoomFactor);
        buildGrid(gridCanvas, zoomFactor);
    }

    public void buildGrid(Canvas canvas, double zoomFactor) {
        double DPI = Screen.getPrimary().getDpi();
        double height = zoomFactor * Converter.toPixels(DRAW_AREA_HEIGHT, DPI),
                width = zoomFactor * Converter.toPixels(DRAW_AREA_WIDTH, DPI);
        canvas.setHeight(height);
        canvas.setWidth(width);
        canvas.setMouseTransparent(true);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.TRANSPARENT.toFXColor());
        gc.clearRect(0, 0, width, height);
        gc.fillRect(0, 0, width, height);

        if (gridSizeProperty.get() == 0)
            return;

        gc.setStroke(Color.GRAY.fade(DEFAULT_GRID_OPACITY).toFXColor());
        gc.setLineWidth(1);
        double size = zoomFactor * Converter.toPixels(gridSizeProperty.get(), DPI);
        for (double i = 0; i < gc.getCanvas().getWidth(); i += size) {
            gc.strokeLine(i, 0, i, gc.getCanvas().getHeight());
        }
        for (double i = 0; i < gc.getCanvas().getHeight(); i += size) {
            gc.strokeLine(0, i, gc.getCanvas().getWidth(), i);
        }
        this.gridCanvas = canvas;
    }

    public void buildDrawingArea(Canvas canvas, double zoomFactor) {
        double DPI = Screen.getPrimary().getDpi();
        double height = zoomFactor * Converter.toPixels(DRAW_AREA_HEIGHT, DPI),
                   width = zoomFactor * Converter.toPixels(DRAW_AREA_WIDTH, DPI);
        double sheetHeight = Converter.toPixels(format.getHeight(), DPI),
                   sheetWidth = Converter.toPixels(format.getWidth(), DPI);
        canvas.setWidth(width);
        canvas.setHeight(height);
        GraphicsContext context = canvas.getGraphicsContext2D();
        shapeManager.setContext(context);
        context.clearRect(0, 0, width, height);
        context.setFill(Color.WHITE.toFXColor());
        context.fillRect(0, 0, width, height);
        if(format != SheetFormat.NONE) {
            double padX = Converter.toPixels((DRAW_AREA_WIDTH - getFormat().getWidth()) / 2, DPI);
            double padY = Converter.toPixels((DRAW_AREA_HEIGHT - getFormat().getHeight()) / 2, DPI);
            context.strokeRect(padX, padY, sheetWidth, sheetHeight);
        }
    }
}
