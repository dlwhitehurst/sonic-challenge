/**
 * 
 */
package com.sonic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sonic.common.Constants;
import com.sonic.domain.util.CurrencyUtil;

import java.math.BigDecimal;

/**
 * This class represents the immutable Item (either service or material). It may be used in a Hashtable
 * in the near future. This class overrides toString(), hashCode(), and equals(Object).
 * 
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public final class Item extends BaseObject {

	/**
	 * unique serial class identifier
	 */
	private static final long serialVersionUID = -2947977059822270139L;

	/**
	 * unique identifier for persistence methods and needs to persist through
	 * using code to maintain its uniqueness
	 */
	private final Long key;
	
	/**
	 * item name or descriptive identifier
	 */
	private final String name;
	
	/**
	 * cost of item
	 */
	private final BigDecimal price;
	
	
	/**
	 * Default constructor should only initialize with values that mean nothing. One could create
	 * and throw a RuntimeException.
	 */
	public Item() {
		this.key = (-1L);
		this.name = "Invalid item construction.";
		BigDecimal tmp = new BigDecimal("0.00");
		tmp = tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.price = tmp;
	}
	
	/**
	 * Our constructor creates a "valuable" object with one call. The requirements call for the object
	 * to be immutable. Remove the final specifiers on the fields if, and only if the object needs to 
	 * allow for revision. NOTE: I would change constructor to take a string price because this should
	 * be validated for a currency format.
	 *  
	 * @param aKey unique numeric identifier
	 * @param aName descriptive term for item
	 * @param aPrice cost
	 */
	public Item(final Long aKey, final String aName, final String aPrice) {
		
		/**
		 * according to the method of persistence, this may or may not be needed
		 */
		if (aKey == null) {
			this.key = -1L;
		} else {
			this.key = aKey;
		}
		if (aName == null || aName.equals("")) {
			this.name = "Invalid name or null value";
		} else {
			this.name = aName;
		}
		if (acceptablePriceFormat(aPrice)) {
			BigDecimal tmp = new BigDecimal(aPrice);
			tmp = tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
			this.price = tmp;
		} else {
			BigDecimal tmp = new BigDecimal("0.00");
			tmp = tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
			this.price = tmp;
					
		}
	}
	
	public Long getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * This methods returns a human-readable string of key-value pairs using Apache's common-lang3 library
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("key", this.key.toString())
                .append("name", this.name)
                .append("price", CurrencyUtil.getBigDecimalCurrencyString(this.price)).toString();
	}

	/**
	 * This overriden method compares objects for equivalence using hashcode. This should
	 * be sufficient for use in our context.
	 */
	@Override
	public boolean equals(final Object o) {
	       if (this == o) {
	            return true;
	        }
	        if (!(o instanceof Item)) {
	            return false;
	        }

	        final Item tmp = (Item) o;

	        return this.hashCode() == tmp.hashCode();	
	}

	/**
	 * This method creates a hashCode using the hashCode implementations (overridden or not) in the
	 * Java types used as members of this class.
	 */
	@Override
	public int hashCode() {
		   int result;
	        result = (this.key != null ? this.key.hashCode() : 0);
	        result = Constants.MAGIC_HASHCODE_FACTOR * result + (this.name != null ? this.name.hashCode() : 0);
	        result = Constants.MAGIC_HASHCODE_FACTOR * result + (this.price != null ? this.price.hashCode() : 0);
	        return result;	
	}
	
	private boolean acceptablePriceFormat(final String aPrice) {
		boolean retVal = true;
		
		if (!aPrice.matches("^\\d+(\\.\\d{2})?$")) {
			retVal = false;
			System.out.println("INFO: Currency string improperly formatted! " + aPrice);
		}
		return retVal;
	}
}
