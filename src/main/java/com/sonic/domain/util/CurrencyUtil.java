package com.sonic.domain.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.sonic.common.Constants;

/**
 * This is a utility class for handling currency.
 *
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 */
public final class CurrencyUtil {

    /**
     * This is for default construction prevention.
     */
    private CurrencyUtil() {
    }

    /**
     * This static method returns a (textual) number format for currency.
     *
     * @return textual number format for US currency representation when
     *         converting numerical objects to strings
     */
    public static NumberFormat getCurrencyFormat() {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        return n;
    }

    /**
     * This method returns a string representation for money stored as
     * BigDecimal.
     *
     * @param bd
     *            the numeric currency value
     * @return string representation of big decimal object
     */
    public static String getBigDecimalCurrencyString(final BigDecimal bd) {
        BigDecimal value = bd;

        value = value.setScale(Constants.US_DECIMAL_PLACES,
                BigDecimal.ROUND_HALF_UP);

        NumberFormat nf = getCurrencyFormat();
        double money = value.doubleValue();
        String bdcs = nf.format(money);
        return bdcs;
    }

    /**
     * This method determines if a String is acceptable for the creation of a
     * BigDecimal currency object.
     *
     * @param aPrice the string currency designation
     * @return true/false indication of string designation
     */
    public static boolean acceptableStringCurrencyFormat(final String aPrice) {
        boolean retVal = true;

        if (!aPrice.matches("^\\d+(\\.\\d{2})?$")) {
            retVal = false;
        }
        return retVal;
    }

}
