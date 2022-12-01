package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.RectangleTool;

public class RectangleToolTest {

    private final CommandManager commandManager = new CommandManager();
    private final ShapeManager shapeManager = new ShapeManager(null, commandManager);
    private final RectangleTool rectangleTool = new RectangleTool(shapeManager);

    @Test
    @DisplayName("Test mouseDown")
    void testMouseDown() {
        assertNull(rectangleTool.getStart());
        rectangleTool.mouseDown(1, 1);
        assertEquals(1.0, rectangleTool.getStart().getX());
        assertEquals(1.0, rectangleTool.getStart().getY());
        assertNull(rectangleTool.getEnd());
        rectangleTool.mouseDown(5, 5);
        assertEquals(5.0, rectangleTool.getEnd().getX());
        assertEquals(5.0, rectangleTool.getEnd().getY());
    }
}
