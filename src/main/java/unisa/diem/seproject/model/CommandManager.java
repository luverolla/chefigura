package unisa.diem.seproject.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager {
    private final Deque<Command> commandStack;
    private final Deque<Command> undoStack;

    public CommandManager() {
        commandStack = new ArrayDeque<>();
        undoStack = new ArrayDeque<>();
    }

    public void execute(Command command) {
        command.execute();
        commandStack.push(command);
    }

    public void rollback() {
        if (commandStack.isEmpty()) {
            return;
        }
        Command command = commandStack.pop();
        command.rollback();
        undoStack.push(command);
    }

    public Command lastExecutedCommand() { //Used for testing purposes only
        return commandStack.peekFirst();
    }

    public Command lastUndoneCommand() { //Used for testing purposes only
        return undoStack.peekFirst();
    }
}
