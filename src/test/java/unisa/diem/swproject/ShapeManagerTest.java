package unisa.diem.swproject;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import unisa.diem.swproject.model.CommandManager;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.ShapeManager;
import unisa.diem.swproject.model.shapes.RectangleShape;

public class ShapeManagerTest {
    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);

    @Test
    public void testCountShapes() {
        assert sm.countShapes() == 0;
    }

    @Test
    public void testShapeIsPresent() {
        Point2D p1 = new Point2D(0, 0);
        Shape s = new RectangleShape(p1, p1);
        assert ! sm.shapeIsPresent(s);
    }
}
