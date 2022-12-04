package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeManagerTest {

    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);

    @Test
    @DisplayName("Test add")
    public void testAdd() {
        Shape shape = new RectangleShape(new Point(0, 0), new Point(10, 10));
        sm.add(shape);
        assertNotNull(sm.getShape(shape));
    }

    @Test
    @DisplayName("Test remove")
    public void testRemove() {
        Shape shape = new RectangleShape(new Point(0, 0), new Point(10, 10));
        sm.add(shape);
        sm.remove(shape);
        assertNull(sm.getShape(shape));
    }

    @Test
    @DisplayName("Test clear")
    public void testClear() {
        Shape shape1 = new RectangleShape(new Point(0, 0), new Point(10, 10));
        Shape shape2 = new RectangleShape(new Point(1, 1), new Point(11, 11));
        sm.add(shape1);
        sm.add(shape2);
        sm.clear();
        assertTrue(sm.isEmpty());
    }
}
