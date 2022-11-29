package unisa.diem.seproject;

import org.junit.jupiter.api.Test;

import unisa.diem.seproject.model.*;
import unisa.diem.seproject.model.commands.ShapeDrawCommand;
import unisa.diem.seproject.model.extensions.Point;
import unisa.diem.seproject.model.shapes.RectangleShape;

public class ShapeDrawCommandTest {
    final CommandManager cm = new CommandManager();
    final Sheet sheet = new Sheet(SheetFormat.NONE, cm);
    final Shape testRectangle = new RectangleShape(new Point(0, 0), new Point(10, 10));
    final ShapeDrawCommand command = new ShapeDrawCommand(testRectangle, sheet.shapeManager());

    @Test
    public void testExecute() {
        cm.execute(command);
        assert sheet.shapeManager().countShapes() == 1;
        assert sheet.shapeManager().shapeIsPresent(testRectangle);
        assert cm.lastExecutedCommand().equals(command);
    }
}
