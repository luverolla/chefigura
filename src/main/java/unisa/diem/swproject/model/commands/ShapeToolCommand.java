package unisa.diem.swproject.model.commands;

import unisa.diem.swproject.model.BaseCommand;
import unisa.diem.swproject.model.Shape;
import unisa.diem.swproject.model.Tool;

public class ShapeToolCommand extends BaseCommand {
    private final Tool tool;

    private Shape shape;

    public ShapeToolCommand(Tool tool) {
        this.tool = tool;
        this.shape = null;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public int execute() {
        tool.apply();
        shape = tool.getShape();
        return 0;
    }

    @Override
    public int rollback() {
        tool.revert();
        shape = tool.getShape();
        return 0;
    }
}
