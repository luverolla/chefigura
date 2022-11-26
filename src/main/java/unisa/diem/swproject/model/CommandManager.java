package unisa.diem.swproject.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager {
    private final Deque<Command> commandStack;
    private final Deque<Command> undoStack;

    public CommandManager() {
        commandStack = new ArrayDeque<>();
        undoStack = new ArrayDeque<>();
    }

    public int execute(Command command) { // this method is defined as an int in order to use the return value for unit testing
        command.execute();
        commandStack.push(command);
        return 1;
    }

    public int undo() { // this method is defined as an int in order to use the return value for unit testing
        if (commandStack.isEmpty()) {
            return 0;
        }
        Command command = commandStack.pop();
        undoStack.push(command);
        command.rollback();
        return 1;
    }

    public int redo() throws CloneNotSupportedException { // this method is defined as an int in order to use the return value for unit testing
        if (undoStack.isEmpty()) {
            return 0;
        }
        Command command = undoStack.pop();
        command.execute();
        commandStack.push(command);
        return 1;
    }

    public Command lastCommand() {
        return commandStack.peek();
    }
}