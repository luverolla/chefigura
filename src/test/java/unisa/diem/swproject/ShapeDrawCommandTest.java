package unisa.diem.swproject;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import unisa.diem.swproject.model.*;
import unisa.diem.swproject.model.commands.ShapeDrawCommand;
import unisa.diem.swproject.model.shapes.RectangleShape;

public class ShapeDrawCommandTest {
    final CommandManager cm = new CommandManager();
    final Sheet sheet = new Sheet("test-sheet", SheetFormat.NONE, cm);
    final Shape testRectangle = new RectangleShape(new Point2D(0, 0), new Point2D(10, 10));
    final ShapeDrawCommand command = new ShapeDrawCommand(testRectangle, sheet.shapeManager());

    @Test
    public void executeCommand() {
        cm.execute(command);
        assert sheet.shapeManager().countShapes() == 1;
        assert sheet.shapeManager().shapeIsPresent(testRectangle);
        assert cm.lastCommand() != null;
    }
}
