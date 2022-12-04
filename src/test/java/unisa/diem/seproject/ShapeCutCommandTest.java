package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.Sheet;
import unisa.diem.seproject.model.SheetFormat;
import unisa.diem.seproject.model.commands.ShapeCutCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeCutCommandTest {

    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    private final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final ShapeCutCommand command = new ShapeCutCommand(sheet.shapeManager(), testRectangle);

    @Test
    @DisplayName("Test execute of ShapeCutCommand")
    public void testExecute() {
        sheet.shapeManager().add(testRectangle);
        cm.execute(command);
        assertTrue(sheet.shapeManager().isEmpty());
    }

    @Test
    @DisplayName("Test rollback of ShapeCutCommand")
    public void testRollback() {
        cm.execute(command);
        cm.undo();
        assertNotNull(sheet.shapeManager().getShape(testRectangle));
    }
}
