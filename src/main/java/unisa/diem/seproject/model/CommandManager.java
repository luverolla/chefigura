package unisa.diem.seproject.model;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class that handles command executions, undo and redo
 *<p>
 * Implements the "Invoker" class of the "Command" pattern
 * </p>
 */
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

    public void undo() {
        if (commandStack.isEmpty()) {
            return;
        }
        Command command = commandStack.pop();
        command.rollback();
        undoStack.push(command);
    }

    public void redo() {
        if (undoStack.isEmpty())
            return;
        Command command = undoStack.pop();
        command.execute();
        commandStack.push(command);
    }

    public Command lastExecutedCommand() {
        return commandStack.peekFirst();
    }

    public Command lastUndoneCommand() {
        return undoStack.peekFirst();
    }
}
