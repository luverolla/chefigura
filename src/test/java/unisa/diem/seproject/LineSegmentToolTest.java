package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.CommandManager;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.tools.LineSegmentTool;

public class LineSegmentToolTest {

    CommandManager commandManager = new CommandManager();
    ShapeManager shapeManager = new ShapeManager(null,commandManager);
    LineSegmentTool lineSegmentTool = new LineSegmentTool(shapeManager);

    @Test
    @DisplayName("Test mouseDown")
    void testMouseDown() {
        assertNull(lineSegmentTool.getStart());
        lineSegmentTool.mouseDown(1,1);
        assertEquals(1.0, lineSegmentTool.getStart().getX());
        assertEquals(1.0, lineSegmentTool.getStart().getY());
        assertNull(lineSegmentTool.getEnd());
        lineSegmentTool.mouseDown(5,5);
        assertEquals(5.0, lineSegmentTool.getEnd().getX());
        assertEquals(5.0, lineSegmentTool.getEnd().getY());
    }
}
