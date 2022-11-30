package unisa.diem.seproject;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.Command;
import unisa.diem.seproject.model.CommandManager;

public class CommandManagerTest {

    private final CommandManager cm = new CommandManager();

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

    private final CommandSample cs = new CommandSample();

        @Test
        @DisplayName("Test execute with a sample command")
        public void testExecute() {
            assertNull(cm.lastExecutedCommand());
            cm.execute(cs);
            assertEquals(1, cs.getCount());
            assert cm.lastExecutedCommand().equals(cs);
        }

        @Test
        @DisplayName("Test rollback with a sample command")
        public void testRollback() {
            assertNull(cm.lastUndoneCommand());
            cm.execute(cs);
            assertEquals(1, cs.getCount());
            cm.rollback();
            assertEquals(0, cs.getCount());
            assert cm.lastUndoneCommand().equals(cs);
            assertNull(cm.lastExecutedCommand());
        }
}
