package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.commands.ShapeResizeCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeResizeCommandTest {
    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);
    private final RectangleShape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final Command resizeCommand = new ShapeResizeCommand(sm, testRectangle, 1.5);

    @Test
    @DisplayName("Test execute of resize command")
    public void testExecute() {
        resizeCommand.execute();
        assertEquals(15, testRectangle.getBounds().getHeight());
        assertEquals(15, testRectangle.getBounds().getWidth());
    }

    @Test
    @DisplayName("Test rollback of resize command")
    public void testRollback() {
        resizeCommand.execute();
        resizeCommand.rollback();
        assertEquals(10, testRectangle.getBounds().getHeight());
        assertEquals(10, testRectangle.getBounds().getWidth());
    }
}
