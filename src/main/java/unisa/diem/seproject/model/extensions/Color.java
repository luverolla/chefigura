package unisa.diem.seproject.model.extensions;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class that represents a color
 *<p>
 * Used instead of the JavaFX Color class to allow serialization
 * </p>
 */
public class Color implements Serializable {

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    public static final Color WHITE = new Color(1, 1, 1);
    public static final Color GRAY = new Color(0.5, 0.5, 0.5);
    private final double red;
    private final double green;
    private final double blue;
    private final double opacity;

    public Color(double red, double green, double blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public Color(double red, double green, double blue) {
        this(red, green, blue, 1);
    }

    public Color(int red, int green, int blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public Color(int red, int green, int blue) {
        this(red, green, blue, 1);
    }

    public Color(javafx.scene.paint.Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public double getOpacity() {
        return opacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, opacity);
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Color))
            return false;
        Color otherColor = (Color) other;
        return red == otherColor.red &&
               green == otherColor.green &&
               blue == otherColor.blue &&
               opacity == otherColor.opacity;
    }

    /**
     * Converts the given color to a JavaFX Paint object.
     * @return The JavaFX Paint object.
     */
    public javafx.scene.paint.Color toFXColor() {
        return new javafx.scene.paint.Color(getRed(), getGreen(), getBlue(), getOpacity());
    }

    /**
     * Make a color more transparent.
     * @param opacity The opacity to set.
     * @return The new color.
     */
    public Color fade(double opacity) {
        return new Color(getRed(), getGreen(), getBlue(), opacity);
    }
}
