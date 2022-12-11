package unisa.diem.seproject.model.commands;

import unisa.diem.seproject.model.Shape;
import unisa.diem.seproject.model.ShapeManager;
import unisa.diem.seproject.model.extensions.Color;
import unisa.diem.seproject.model.Command;

public class ShapeColorCommand implements Command {
    private final ShapeManager sm;
    private final Shape shape;
    private final Color oldStrokeColor;
    private final Color oldFillColor;
    private final Color strokeColor;
    private final Color fillColor;

    public ShapeColorCommand(ShapeManager sm, Shape shape, Color oldStrokeColor, Color oldFillColor, Color strokeColor, Color fillColor) {
        this.sm = sm;
        this.shape = shape;
        this.oldStrokeColor = oldStrokeColor;
        this.oldFillColor = oldFillColor;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    @Override
    public void execute() {
        sm.changeShapeColor(shape, strokeColor, fillColor);
    }

    @Override
    public void rollback() {
        sm.changeShapeColor(shape,oldStrokeColor, oldFillColor);
    }
}
