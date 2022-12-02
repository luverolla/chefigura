package unisa.diem.seproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.Sheet;
import unisa.diem.seproject.model.SheetFormat;
import unisa.diem.seproject.model.commands.ShapePasteCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapePasteCommandTest {
    final CommandManager cm = new CommandManager();
    final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    final ShapePasteCommand command = new ShapePasteCommand(sheet.shapeManager(), testRectangle);

    @Test
    @DisplayName("Test execute of ShapePasteCommand")
    public void testExecute() {
        cm.execute(command);
        assertEquals(sheet.shapeManager().getShapes().size(), 1);
    }

    @Test
    @DisplayName("Test rollback of ShapePasteCommand")
    public void testRollback() {
        cm.execute(command);
        cm.rollback();
        assertEquals(sheet.shapeManager().getShapes().size(), 0);
    }
}
