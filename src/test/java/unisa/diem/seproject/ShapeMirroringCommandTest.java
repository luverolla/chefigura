package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.commands.ShapeMirroringCommand;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.LineSegmentShape;

public class ShapeMirroringCommandTest {
    private final CommandManager cm = new CommandManager();
    private final ShapeManager sm = new ShapeManager(null, cm);
    private final LineSegmentShape testLine = new LineSegmentShape(Color.BLACK, new Point(10, 10), new Point(0, 0));
    private final Command commandH = new ShapeMirroringCommand(sm, testLine, true);
    private final Command commandV = new ShapeMirroringCommand(sm, testLine, false);

    @Test
    @DisplayName("Test execute of mirroring command")
    public void testExecute() {
        sm.add(testLine);
        commandH.execute();
        assertEquals(0, testLine.getStart().getX());
        assertEquals(10, testLine.getStart().getY());
        assertEquals(10, testLine.getEnd().getX());
        assertEquals(0, testLine.getEnd().getY());
        commandV.execute();
        assertEquals(0, testLine.getStart().getX());
        assertEquals(0, testLine.getStart().getY());
        assertEquals(10, testLine.getEnd().getX());
        assertEquals(10, testLine.getEnd().getY());
    }

    @Test
    @DisplayName("Test rollback of mirroring command")
    public void testRollback() {
        sm.add(testLine);
        commandH.execute();
        commandH.rollback();
        assertEquals(10, testLine.getStart().getX());
        assertEquals(10, testLine.getStart().getY());
        assertEquals(0, testLine.getEnd().getX());
        assertEquals(0, testLine.getEnd().getY());
        commandV.execute();
        commandV.rollback();
        assertEquals(10, testLine.getStart().getX());
        assertEquals(10, testLine.getStart().getY());
        assertEquals(0, testLine.getEnd().getX());
        assertEquals(0, testLine.getEnd().getY());
    }
}
