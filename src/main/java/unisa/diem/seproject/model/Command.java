package unisa.diem.seproject.model;

/**
 * Generic command
 */
public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Rollback to state previous to command execution
     */
    void rollback();
}
