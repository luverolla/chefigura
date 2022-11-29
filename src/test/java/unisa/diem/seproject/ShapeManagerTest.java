package unisa.diem.seproject;

import org.junit.jupiter.api.Test;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeManagerTest {
    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);

    @Test
    public void testCountShapes() {
        assert sm.countShapes() == 0;
    }

    @Test
    public void testShapeIsPresent() {
        Point p1 = new Point(0, 0);
        Shape s = new RectangleShape(p1, p1);
        assert ! sm.shapeIsPresent(s);
    }
}
