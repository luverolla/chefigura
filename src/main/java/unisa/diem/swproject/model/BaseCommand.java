package unisa.diem.swproject.model;

public abstract class BaseCommand implements Command {

    public Command then(Command next) {
        return new Command() {
            @Override
            public int execute() throws CloneNotSupportedException {
                int result = BaseCommand.this.execute();
                if (result == 0) {
                    result = next.execute();
                }
                return result;
            }

            @Override
            public int rollback() {
                int result = next.rollback();
                if (result == 0) {
                    result = BaseCommand.this.rollback();
                }
                return result;
            }
        };
    }
}
