package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.commands.ShapeRotationCommand;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.LineSegmentShape;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeRotationCommandTest {

    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);
    private final LineSegmentShape testLine = new LineSegmentShape(Color.BLACK, new Point(10, 10), new Point(20, 10));
    private final double angle = 45;
    private final Command command = new ShapeRotationCommand(sm, testLine, angle);

    @Test
    @DisplayName("Test execute of rotation command")
    public void testExecute() {
        sm.add(testLine);
        command.execute();
        assertEquals(angle, testLine.getAngle());
    }

    @Test
    @DisplayName("Test rollback of rotation command")
    public void testRollback() {
        sm.add(testLine);
        command.execute();
        command.rollback();
        assertEquals(0, testLine.getAngle());
    }
}
