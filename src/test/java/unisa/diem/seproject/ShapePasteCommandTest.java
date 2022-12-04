package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.Sheet;
import unisa.diem.seproject.model.SheetFormat;
import unisa.diem.seproject.model.commands.ShapePasteCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapePasteCommandTest {

    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    private final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final ShapePasteCommand command = new ShapePasteCommand(sheet.shapeManager(), testRectangle);

    @Test
    @DisplayName("Test execute of ShapePasteCommand")
    public void testExecute() {
        cm.execute(command);
        assertNotNull(sheet.shapeManager().getShape(command.getPasted()));
    }

    @Test
    @DisplayName("Test rollback of ShapePasteCommand")
    public void testRollback() {
        cm.execute(command);
        cm.undo();
        assertTrue(sheet.shapeManager().isEmpty());
    }
}
