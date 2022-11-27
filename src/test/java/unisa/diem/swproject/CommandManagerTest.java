package unisa.diem.swproject;


import org.junit.jupiter.api.Test;
import unisa.diem.swproject.model.CommandManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandManagerTest {
    private final CommandManager cm = new CommandManager();
    private final CommandSample cs = new CommandSample();

    @Test
    public void testExecute() {
        assert cm.lastCommand() == null;
        cm.execute(cs);
        assertEquals(1, CommandSample.count);
        assert cm.lastCommand().equals(cs);
        assert cm.lastCommand() == null;
    }
}
