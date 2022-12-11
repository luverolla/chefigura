package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.Sheet;
import unisa.diem.seproject.model.commands.ShapeDeleteCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeDeleteCommandTest {

    private final CommandManager cm = new CommandManager();
    private final Sheet sheet = new Sheet(cm);
    private final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    private final ShapeDeleteCommand command = new ShapeDeleteCommand(sheet.shapeManager(), testRectangle);


    @Test
    @DisplayName("Test execute of ShapeDeleteCommand")
    public void testExecute() {
        sheet.shapeManager().add(testRectangle);
        cm.execute(command);
        assertNull(sheet.shapeManager().getShape(testRectangle));
    }

    @Test
    @DisplayName("Test rollback of ShapeDeleteCommand")
    public void testRollback() {
        sheet.shapeManager().add(testRectangle);
        cm.execute(command);
        cm.undo();
        assertNotNull(sheet.shapeManager().getShape(testRectangle));
    }
}
