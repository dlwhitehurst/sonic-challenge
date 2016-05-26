/**
 * 
 */
package com.sonic.domain;

/**
 * This class is an order item of type service (work offering) and is not taxable.
 * 
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class ServiceOrderItem extends OrderItem {

	/**
	 * unique serial class identifier
	 */
	private static final long serialVersionUID = -7947058873298639266L;

	/**
	 * Constructor
	 * @param anItem
	 * @param aQuantity
	 */
	public ServiceOrderItem(final Item anItem, final Integer aQuantity) {
		this.item = anItem;
		this.quantity = aQuantity;
	}
	
	@Override
	public final boolean isTaxable() {
		return false;
	}

}
