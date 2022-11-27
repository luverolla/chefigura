package unisa.diem.swproject.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager {
    private final Deque<Command> commandStack;

    public CommandManager() {
        commandStack = new ArrayDeque<>();
    }

    public void execute(Command command) {
        command.execute();
        commandStack.push(command);
    }

    public Command lastCommand() { //Used for testing purposes only
        if (commandStack.isEmpty()) {
            return null;
        }
        return commandStack.remove();
    }
}
