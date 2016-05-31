/**
 * 
 */
package com.sonic.common;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author guita
 *
 */
public class TestConstants {
    

    @Test
    public void testHashCodeFactor() {
        int factor = Constants.MAGIC_HASHCODE_FACTOR;
        assertEquals(factor, 29);
    }

    @Test
    public void testDecimalPlaces() {
        int digits = Constants.US_DECIMAL_PLACES;
        assertEquals(digits, 2);
    }

}
