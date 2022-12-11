package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.commands.ShapeStretchCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeStretchCommandTest {
    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);
    private final RectangleShape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final Command stretchCommand = new ShapeStretchCommand(sm, testRectangle, 5, 0, 1);

    @Test
    @DisplayName("Test execute of stretch command")
    public void testExecute() {
        sm.add(testRectangle);
        stretchCommand.execute();
        assertEquals(15, testRectangle.getBounds().getWidth());
        assertEquals(10, testRectangle.getBounds().getHeight());
    }

    @Test
    @DisplayName("Test rollback of stretch command")
    public void testRollback() {
        sm.add(testRectangle);
        stretchCommand.execute();
        stretchCommand.rollback();
        assertEquals(10, testRectangle.getBounds().getWidth());
        assertEquals(10, testRectangle.getBounds().getHeight());
    }
}
