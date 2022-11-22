package unisa.diem.swproject.model;

public interface Command {
    int execute(); // this method is defined as an int in order to use the return value for unit testing
    int rollback(); // this method is defined as an int in order to use the return value for unit testing
}