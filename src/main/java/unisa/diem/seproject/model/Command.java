package unisa.diem.seproject.model;

public interface Command {
    void execute();
    void rollback();
}
