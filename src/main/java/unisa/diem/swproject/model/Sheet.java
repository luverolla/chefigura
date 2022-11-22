package unisa.diem.swproject.model;

import javafx.scene.canvas.Canvas;

public class Sheet extends Canvas {
    private String name;
    private SheetFormat format;
    private double zoomLevel;
    private Tool currentTool;

    public Sheet(String name, SheetFormat format, double zoomLevel, Tool currentTool) {
        this.name = name;
        this.format = format;
        this.zoomLevel = zoomLevel;
        this.currentTool = currentTool;
    }
    public void drawGrid(Graphics g, double size) {
        nrows = format.getHeight() / size;
        ncolumns = format.getWidth() / size;

        //draw the rows
        for (int i = 0; i < nrows; i++) {
            g.drawLine(0, i * size, format.getWidth(), i * size);
        }

        //draw the columns
        for (int i = 0; i < ncolumns; i++) {
            g.drawLine(i * size, 0, i * size, format.getHeight());
        }
    }

    public void zoom(double factor) {

        //Zoom in
        if (factor < 0) {
            zoomLevel *= factor;
            redraw();
        }
        //Zoom out
        if (factor > 0) {
            zoomLevel /= factor;
            redraw();
        }
    }
}
