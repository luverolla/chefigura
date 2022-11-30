package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.Sheet;
import unisa.diem.seproject.model.SheetFormat;
import unisa.diem.seproject.model.commands.ShapeDeleteCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeDeleteCommandTest {

final CommandManager cm = new CommandManager();
    final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    final ShapeDeleteCommand command = new ShapeDeleteCommand(sheet.shapeManager(), testRectangle);


    @Test
    @DisplayName("Test execute of ShapeDeleteCommand")
public void testExecute() {
        sheet.shapeManager().add(testRectangle);
        cm.execute(command);
        assertFalse(sheet.shapeManager().getShapes().contains(testRectangle));
    }

    @Test
    @DisplayName("Test rollback of ShapeDeleteCommand")
public void testRollback() {
        sheet.shapeManager().add(testRectangle);
        cm.execute(command);
        cm.rollback();
        assertTrue(sheet.shapeManager().getShapes().contains(testRectangle));
    }

}
