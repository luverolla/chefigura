package unisa.diem.seproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.Sheet;
import unisa.diem.seproject.model.SheetFormat;
import unisa.diem.seproject.model.commands.ShapeCutCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeCutCommandTest {
    final CommandManager cm = new CommandManager();
    final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    final ShapeCutCommand command = new ShapeCutCommand(sheet.shapeManager(), testRectangle);

    @Test
    @DisplayName("Test execute of ShapeCutCommand")
    public void testExecute() {
        sheet.shapeManager().add(testRectangle);
        cm.execute(command);
        assertEquals(sheet.shapeManager().getShapes().size(), 0);
    }

    @Test
    @DisplayName("Test rollback of ShapeCutCommand")
    public void testRollback() {
        cm.execute(command);
        cm.undo();
        assertEquals(sheet.shapeManager().getShapes().size(), 1);
    }

}
