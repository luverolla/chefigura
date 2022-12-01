package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.EllipseTool;

public class EllipseToolTest {

    private final CommandManager commandManager = new CommandManager();
    private final ShapeManager shapeManager = new ShapeManager(null, commandManager);
    private final EllipseTool ellipseTool = new EllipseTool(shapeManager);

    @Test
    @DisplayName("Test mouseDown")
    void testMouseDown() {
        assertNull(ellipseTool.getCenter());
        ellipseTool.mouseDown(1, 1);
        assertEquals(1.0, ellipseTool.getCenter().getX());
        assertEquals(1.0, ellipseTool.getCenter().getY());
        assertNull(ellipseTool.getEnd());
        ellipseTool.mouseDown(5, 5);
        assertEquals(5.0, ellipseTool.getEnd().getX());
        assertEquals(5.0, ellipseTool.getEnd().getY());
    }
}
