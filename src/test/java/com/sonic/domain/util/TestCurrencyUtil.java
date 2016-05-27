/**
 * 
 */
package com.sonic.domain.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.junit.Test;

/**
 * @author dlwhitehurst
 *
 */
public class TestCurrencyUtil {

	private BigDecimal bd1;
	private BigDecimal bd2;
	private BigDecimal bd3;
	
	@Test
	public void testGetBigDecimalCurrencyString() {
		bd1 = new BigDecimal("254664332.78");
		bd2 = new BigDecimal("34.99");
		bd3 = new BigDecimal("345677.3467");

		String s1 = CurrencyUtil.getBigDecimalCurrencyString(bd1);
		String s2 = CurrencyUtil.getBigDecimalCurrencyString(bd2);
		String s3 = CurrencyUtil.getBigDecimalCurrencyString(bd3);
		
		assertNotNull(s1);
		assertNotNull(s2);
		assertNotNull(s3);
		
		assertEquals(s1,"$254,664,332.78");
		assertEquals(s2,"$34.99");
		assertEquals(s3,"$345,677.35");
		
	}

	@Test
	public void testGetCurrencyNumberFormat() {
		NumberFormat nf = CurrencyUtil.getCurrencyFormat();
		assertNotNull(nf);
	}
	
	@Test
	public void testGetStringCurrencyReps() {
	    String tmp = "334.23";
	    boolean test = CurrencyUtil.acceptableStringCurrencyFormat(tmp);
	    assertTrue(test);
	 
	}
}
