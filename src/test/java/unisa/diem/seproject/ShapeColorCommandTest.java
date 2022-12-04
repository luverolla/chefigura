package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeColorCommand;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeColorCommandTest {

    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    private final ClosedShape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final Color oldStrokeColor = testRectangle.getStrokeColor();
    private final Color oldFillColor = testRectangle.getFillColor();
    private final Color strokeColor = new Color(1, 1, 1);
    private final Color fillColor = new Color(1, 1, 1);
    private final ShapeColorCommand command = new ShapeColorCommand(sheet.shapeManager(), testRectangle, oldStrokeColor, oldFillColor, strokeColor, fillColor);

    @Test
    @DisplayName("test execute of ShapeColorCommand")
    public void testExecute() {
        cm.execute(command);
        assertEquals(strokeColor, testRectangle.getStrokeColor());
        assertEquals(fillColor, testRectangle.getFillColor());
    }

    @Test
    @DisplayName("test rollback of ShapeColorCommand")
    public void testRollback() {
        cm.execute(command);
        cm.undo();
        assertEquals(oldStrokeColor, testRectangle.getStrokeColor());
        assertEquals(oldFillColor, testRectangle.getFillColor());
    }
}
