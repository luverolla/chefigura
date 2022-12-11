package unisa.diem.seproject.model;

/**
 * Generic command
 * <p>
 *     Implements the homonym class in the "Command" pattern
 * </p>
 */
public interface Command {
    void execute();
    void rollback();
}
