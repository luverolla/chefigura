package unisa.diem.seproject.model;

import java.io.Serializable;

public class SheetFormat implements Serializable {

    private final double width, height;
    public static SheetFormat NONE = new SheetFormat( 0, 0);

    public SheetFormat(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
