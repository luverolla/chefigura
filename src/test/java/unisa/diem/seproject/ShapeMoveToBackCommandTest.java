package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.commands.ShapeMoveToBackCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeMoveToBackCommandTest {
    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);
    private final RectangleShape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final ShapeMoveToBackCommand moveCommand = new ShapeMoveToBackCommand(sm, testRectangle);

    @Test
    @DisplayName("Test execute of move to back command")
    public void testExecute() {
        sm.add(testRectangle);
        moveCommand.execute();
        assertEquals(-1, testRectangle.getZIndex());
    }

    @Test
    @DisplayName("Test rollback of move to back command")
    public void testRollback() {
        sm.add(testRectangle);
        moveCommand.execute();
        moveCommand.rollback();
        assertEquals(0, testRectangle.getZIndex());
    }
}
