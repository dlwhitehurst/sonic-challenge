/**
 * 
 */
package com.sonic.domain;

/**
 * This class represents an order item of type material (tangible item) and is taxable
 * 
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class MaterialOrderItem extends OrderItem {

	/**
	 * unique serial class identifier
	 */
	private static final long serialVersionUID = -3623292236831612014L;

	/**
	 * Constructor
	 * @param _item company item
	 * @param _quantity how many
	 */
	public MaterialOrderItem(Item _item, Integer _quantity) {
		this.item = _item;
		this.quantity = _quantity;
	}
	
	@Override
	public boolean isTaxable() {
		return true;
	}

}
