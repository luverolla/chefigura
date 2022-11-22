package unisa.diem.swproject.model;

public interface Command {
    void execute();
    void rollback();
}
