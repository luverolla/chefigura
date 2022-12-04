package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeColorCommand;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.LineSegmentShape;

public class ShapeColorCommandTest {

    CommandManager cm = new CommandManager();
    Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    Shape testLineSegment = new LineSegmentShape(new Point(0, 0), new Point(10, 10));
    Color oldStrokeColor = testLineSegment.getStrokeColor();
    Color strokeColor = new Color(1, 1, 1);
    Color fillColor = new Color(1, 1, 1);
    ShapeColorCommand command1 = new ShapeColorCommand(sheet.shapeManager(), testLineSegment, oldStrokeColor, null, strokeColor, fillColor);

    @Test
    @DisplayName("test execute of ShapeColorCommand")
    public void testExecute() {
        cm.execute(command1);
        assertEquals(strokeColor, testLineSegment.getStrokeColor());
    }

    @Test
    @DisplayName("test rollback of ShapeColorCommand")
    public void testRollback() {
        cm.execute(command1);
        cm.undo();
        assertEquals(oldStrokeColor, testLineSegment.getStrokeColor());
    }
}
