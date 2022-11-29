package unisa.diem.seproject;


import org.junit.jupiter.api.Test;
import unisa.diem.seproject.model.CommandManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandManagerTest {
    private final CommandManager cm = new CommandManager();
    private final CommandSample cs = new CommandSample();

    @Test
    public void testExecute() {
        assertNull(cm.lastCommand());
        cm.execute(cs);
        assertEquals(1, CommandSample.count);
        assert cm.lastCommand().equals(cs);
        assertNull(cm.lastCommand());
    }
}
