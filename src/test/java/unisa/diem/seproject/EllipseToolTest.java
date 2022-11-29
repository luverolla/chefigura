package unisa.diem.seproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.EllipseTool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EllipseToolTest {
    CommandManager commandManager = new CommandManager();
    ShapeManager shapeManager = new ShapeManager(null, commandManager);
    EllipseTool ellipseTool = new EllipseTool(shapeManager);

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
