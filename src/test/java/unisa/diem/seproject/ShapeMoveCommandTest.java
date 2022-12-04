package unisa.diem.seproject;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeMoveCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.LineSegmentShape;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeMoveCommandTest {

    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    private final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final Shape testLineSegment = new LineSegmentShape(new Point(0, 0), new Point(10, 10));
    double deltaX = 10;
    double deltaY = 10;
    private final Command command1 = new ShapeMoveCommand(sheet.shapeManager(), testRectangle, deltaX, deltaY);
    private final Command command2 = new ShapeMoveCommand(sheet.shapeManager(), testLineSegment, deltaX, deltaY);

    @Test
    @DisplayName("Test execute of ShapeMoveCommand with a rectangle")
    public void testExecuteRectangle() {
        sheet.shapeManager().add(testRectangle);
        Point oldCenter = testRectangle.getBounds().getCenter();
        command1.execute();
        assertEquals(oldCenter.getX() + deltaX, testRectangle.getBounds().getCenter().getX());
        assertEquals(oldCenter.getY() + deltaY, testRectangle.getBounds().getCenter().getY());
    }

    @Test
    @DisplayName("Test execute of ShapeMoveCommand with a line segment")
    public void testExecuteLineSegment() {
        sheet.shapeManager().add(testLineSegment);
        Point oldCenter = testLineSegment.getBounds().getCenter();
        command2.execute();
        assertEquals(oldCenter.getX() + deltaX, testLineSegment.getBounds().getCenter().getX());
        assertEquals(oldCenter.getY() + deltaY, testLineSegment.getBounds().getCenter().getY());
    }

    @Test
    @DisplayName("Test rollback of ShapeMoveCommand with a rectangle")
    public void testRollbackRectangle() {
        sheet.shapeManager().add(testRectangle);
        Point oldCenter = testRectangle.getBounds().getCenter();
        command1.execute();
        command1.rollback();
        assertEquals(oldCenter.getX(), testRectangle.getBounds().getCenter().getX());
        assertEquals(oldCenter.getY(), testRectangle.getBounds().getCenter().getY());
    }

    @Test
    @DisplayName("Test rollback of ShapeMoveCommand with a line segment")
    public void testRollbackLineSegment() {
        sheet.shapeManager().add(testLineSegment);
        Point oldCenter = testLineSegment.getBounds().getCenter();
        command2.execute();
        command2.rollback();
        assertEquals(oldCenter.getX(), testLineSegment.getBounds().getCenter().getX());
        assertEquals(oldCenter.getY(), testLineSegment.getBounds().getCenter().getY());
    }
}
