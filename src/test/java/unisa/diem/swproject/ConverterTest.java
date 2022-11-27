package unisa.diem.swproject;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest { ;

    Converter converter = new Converter();

    @Test
    @DisplayName("Test toMilli, simple case")
    void testToMilli() {

        assertEquals(0.0, converter.toMilli(0,1));
        assertEquals(1.0, converter.toMilli(25.4,1));
    }

    @Test
    @DisplayName("Test toMilli with zero DPI")
    void testToMilliZeroDPI() {
        converter.toMilli(1,0);
    }

    @Test
    @DisplayName("Test toMilli with zero pixels and zero DPI")
    void testToMilliZeroPixelsZeroDPI() {
        converter.toMilli(0,0);
    }

    @Test
    @DisplayName("Test toPixels, simple case")
    void testToPixels() {
        assertEquals(1.0, ((float)converter.toPixels(25.4,1)));
        assertEquals(1/25.4, converter.toPixels(1,1));
    }

    @Test
    @DisplayName("Test toPixels with zero millimeters or zero DPI")
    void testToPixelsZeroMillimetersZeroDPI() {
        assertEquals(0.0, converter.toPixels(0,1));
        assertEquals(0.0, converter.toPixels(1,0));
        assertEquals(0.0, converter.toPixels(0,0));
    }

}