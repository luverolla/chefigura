package unisa.diem.swproject.model;

public class SheetFormat {
    private final double width, height;

    private SheetFormat(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public static SheetFormat NONE = new SheetFormat( 0, 0);
}
