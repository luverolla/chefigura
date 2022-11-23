package unisa.diem.swproject.model;

public class SheetFormat {
    private final String name;
    private final double width, height;

    private SheetFormat(String name, double width, double height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public static SheetFormat A4 = new SheetFormat("A4", 210, 297);
}
