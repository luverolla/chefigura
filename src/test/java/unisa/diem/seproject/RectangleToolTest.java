package unisa.diem.seproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.RectangleTool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RectangleToolTest {
    CommandManager commandManager = new CommandManager();
    ShapeManager shapeManager = new ShapeManager(null, commandManager);
    RectangleTool rectangleTool = new RectangleTool(shapeManager);

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