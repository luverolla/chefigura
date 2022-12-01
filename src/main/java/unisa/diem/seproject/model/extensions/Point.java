package unisa.diem.seproject.model.extensions;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class that represents a point
 *
 * Used instead of the JavaFX Point2D class to allow serialization
 */
public class Point implements Serializable {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Point))
            return false;
        Point otherPoint = (Point) other;
        return otherPoint.x == x && otherPoint.y == y;
    }
}
