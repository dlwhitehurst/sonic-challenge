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
	 * @param _item
	 * @param _quantity
	 */
	public ServiceOrderItem(Item _item, Integer _quantity) {
		this.item = _item;
		this.quantity = _quantity;
	}
	
	@Override
	public boolean isTaxable() {
		return false;
	}

}
