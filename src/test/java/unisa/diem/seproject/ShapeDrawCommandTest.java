package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeDrawCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeDrawCommandTest {

    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(cm);
    private final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final ShapeDrawCommand command = new ShapeDrawCommand(testRectangle, sheet.shapeManager());

    @Test
    @DisplayName("Test execute of a draw command")
    public void testExecute() {
        cm.execute(command);
        assertTrue(sheet.shapeManager().getShapes().contains(testRectangle));
    }

    @Test
    @DisplayName("Test rollback of a draw command")
    public void testRollback() {
        cm.execute(command);
        cm.undo();
        assertFalse(sheet.shapeManager().getShapes().contains(testRectangle));
    }
}
