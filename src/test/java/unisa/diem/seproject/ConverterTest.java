package unisa.diem.seproject;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    @DisplayName("Test toMilli, simple case")
    void testToMilli() {

        assertEquals(0.0, Converter.toMilli(0,1));
        assertEquals(1.0, Converter.toMilli(25.4,1));
    }

    @Test
    @DisplayName("Test toMilli with zero DPI")
    void testToMilliZeroDPI() {
        assertEquals(Double.POSITIVE_INFINITY, Converter.toMilli(1,0));
    }

    @Test
    @DisplayName("Test toMilli with zero pixels and zero DPI")
    void testToMilliZeroPixelsZeroDPI() {
        assertEquals(Double.NaN, Converter.toMilli(0,0));
    }

    @Test
    @DisplayName("Test toPixels, simple case")
    void testToPixels() {
        assertEquals(1.0, ((float)Converter.toPixels(25.4,1)));
        assertEquals(1/25.4, Converter.toPixels(1,1));
    }

    @Test
    @DisplayName("Test toPixels with zero millimeters or zero DPI")
    void testToPixelsZeroMillimetersZeroDPI() {
        assertEquals(0.0, Converter.toPixels(0,1));
        assertEquals(0.0, Converter.toPixels(1,0));
        assertEquals(0.0, Converter.toPixels(0,0));
    }

}