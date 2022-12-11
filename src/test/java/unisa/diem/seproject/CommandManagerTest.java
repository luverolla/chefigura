package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.CommandManager;

public class CommandManagerTest {
    public static class CommandSample implements Command {
        private int count = 0;

        @BeforeEach
        public void init() {
            count = 0;
        }

        @Override
        public void execute() {
            count++;
        }

        @Override
        public void rollback() {
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    private final CommandManager cm = new CommandManager();
    private final CommandSample cs = new CommandSample();

    @Test
    @DisplayName("Test execute with a sample command")
    public void testExecute() {
        assertNull(cm.lastExecutedCommand());
        cm.execute(cs);
        assertEquals(1, cs.getCount());
        assertEquals(cs, cm.lastExecutedCommand());
    }

    @Test
    @DisplayName("Test undo with a sample command")
    public void testUndo() {
        assertNull(cm.lastUndoneCommand());
        cm.execute(cs);
        assertEquals(1, cs.getCount());
        cm.undo();
        assertEquals(0, cs.getCount());
        assertEquals(cs, cm.lastUndoneCommand());
        assertNull(cm.lastExecutedCommand());
    }

    @Test
    @DisplayName("Test redo with a sample command")
    public void testRedo() {
        cm.execute(cs);
        cm.undo();
        cm.redo();
        assertEquals(1, cs.getCount());
        assertNull(cm.lastUndoneCommand());
        assertEquals(cs, cm.lastExecutedCommand());
    }
}
