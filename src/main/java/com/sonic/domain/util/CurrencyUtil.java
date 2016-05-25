package com.sonic.domain.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class CurrencyUtil {

	private static final int US_DECIMAL_PLACES = 2;

	/**
	 * This static method returns a (textual) number format for currency
	 * @return textual number format for US currency representation when converting numerical 
	 * objects to strings
	 */
	public static NumberFormat getCurrencyFormat() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		return n;
	}

	/**
	 * This method returns a string representation for money stored as BigDecimal
	 * @param value the numeric currency value
	 * @return string representation of big decimal object
	 */
	public static String getBigDecimalCurrencyString(BigDecimal value) {
		value = value.setScale(US_DECIMAL_PLACES, BigDecimal.ROUND_HALF_UP);

		NumberFormat nf = getCurrencyFormat();
		double money = value.doubleValue();
		String bdcs = nf.format(money);
		return bdcs;
	}

}
