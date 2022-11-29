package unisa.diem.seproject;

/**
 * Static class for converting between different units of measurement.
 */
public class Converter {
    /**
     * Convert pixels to millimeters
     * @param pix pixels
     * @param DPI screen resolution (dots per inch)
     * @return millimeters
     */
    public static double toMilli(double pix, double DPI) {
        return pix / (25.4 * DPI);
    }

    /**
     * Convert millimeters to pixels
     * @param mm millimeters
     * @param DPI screen resolution (dots per inch)
     * @return pixels
     */
    public static double toPixels(double mm, double DPI) {
        return mm * (1/25.4) * DPI;
    }
}
