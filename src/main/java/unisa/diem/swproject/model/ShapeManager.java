package unisa.diem.swproject.model;

import javafx.scene.canvas.GraphicsContext;

public class ShapeManager {
    private Iterable<Shape> shapes;
    private GraphicsContext context;
    private Shape copiedShape;

    void draw(Tool t) {

    }

    void deleteShape(Shape s) {

    }

    void copyShape(Shape s) {

    }

    void pasteShape() {

    }

    void groupShapes(Iterable<Shape> shapes) {

    }

    void ungroupShapes(ShapeGroup g) {

    }

    void moveToFront(Shape s) {

    }

    void moveToBack(Shape s) {

    }

    void moveForward(Shape s) {

    }

    void moveBackward(Shape s) {

    }

    Shape getSelectedShape(double mouseX, double mouseY) {
        return null;
    }

    void importCustomShape(String libraryName) {

    }

    void exportCustomShape(CustomShape s, String libraryName) {

    }
}