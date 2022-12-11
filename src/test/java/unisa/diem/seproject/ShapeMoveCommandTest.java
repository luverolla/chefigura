package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeMoveCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeMoveCommandTest {
    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(cm);
    private final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final double deltaX = 10;
    private final double deltaY = 10;
    private final Command command = new ShapeMoveCommand(sheet.shapeManager(), testRectangle, deltaX, deltaY);

    @Test
    @DisplayName("Test execute of ShapeMoveCommand")
    public void testExecuteRectangle() {
        Point oldCenter = testRectangle.getBounds().getCenter();
        command.execute();
        assertEquals(oldCenter.getX() + deltaX, testRectangle.getBounds().getCenter().getX());
        assertEquals(oldCenter.getY() + deltaY, testRectangle.getBounds().getCenter().getY());
    }

    @Test
    @DisplayName("Test rollback of ShapeMoveCommand")
    public void testRollbackRectangle() {
        Point oldCenter = testRectangle.getBounds().getCenter();
        command.execute();
        command.rollback();
        assertEquals(oldCenter.getX(), testRectangle.getBounds().getCenter().getX());
        assertEquals(oldCenter.getY(), testRectangle.getBounds().getCenter().getY());
    }
}
