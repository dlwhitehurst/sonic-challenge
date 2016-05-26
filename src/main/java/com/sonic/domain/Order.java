/**
 * 
 */
package com.sonic.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sonic.domain.util.CurrencyUtil;

/**
 * This class represents the client's order which can consist of service or material orderitems (item-qty)
 * 
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public final class Order implements Serializable {

	/**
	 * unique serial class identifier 
	 */
	private static final long serialVersionUID = -34235368252043602L;

	// immutable data
	private final List<OrderItem> order;
	
	// I might restrict the order size to less than static Integer.MAX_VALUE
	private static final Integer MAX_ORDER_SIZE = 1000;

	// we don't want anyone using the default (no-arg) constructor
	public Order() {
		this.order = null;
	}
	
	/**
	 *  I would probably take argument with the architect here for the use of an array, so I'm 
	 *  accepting the constructor for now.
	 * @param theOrder array of order items
	 */
	public Order(OrderItem[] theOrder) {

		if (theOrder == null || theOrder.length == 0 || theOrder.length > MAX_ORDER_SIZE) {
			throw new RuntimeException();
		}

		if (validateOrderPriorToCreation(theOrder)) {
			List<OrderItem> tmp = new ArrayList<OrderItem>();
			for (int i=0; i < theOrder.length; i++) {
				// load our private immutable list
				if (theOrder[i] instanceof OrderItem) {
					tmp.add(theOrder[i]);
				}
			}
			// finalize data structure
			this.order = tmp;
		} else {
			throw new RuntimeException();
		}
		
	}
	
	
	/**
	 * The preferred constructor
	 * @param theOrder list of order items
	 */
	public Order(List<OrderItem> theOrder) {

		if (theOrder == null || theOrder.isEmpty() || theOrder.size() > MAX_ORDER_SIZE) {
			throw new RuntimeException();
		}
		
		if (validateOrderPriorToCreation(theOrder)) {
			this.order = theOrder;
		} else {
			throw new RuntimeException();
		}
	}
	
	/**
	 * This method returns an accurate total based on tax Rate given
	 * @param taxRate input for taxable items
	 * @return numeric order total
	 */
	public BigDecimal getOrderTotal(BigDecimal taxRate) {
		
		BigDecimal total = new BigDecimal("0.00");
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);

		for (OrderItem orderItem: order) {
			if (orderItem.isTaxable()) {
				BigDecimal orderItemPrice = orderItem.getItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
				BigDecimal additionalTax = orderItemPrice.multiply(taxRate);
				total = total.add(orderItemPrice).add(additionalTax);
			} else {
				BigDecimal orderItemPrice = orderItem.getItem().getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
				total = total.add(orderItemPrice);
			}
		}
		return total;
	}

	/**
	 * This method returns a currency formatted string order total
	 * @param taxRate input for taxable items
	 * @return string representation of order total
	 */
	public String getStringOrderTotal(BigDecimal taxRate) {
	    String total = CurrencyUtil.getBigDecimalCurrencyString(getOrderTotal(taxRate));
	    return total;
	}
	
	/**
	 * This returns a sorted list of only OrderItems (type-checked)
	 * @return type-safe list of order items
	 */
	public List<OrderItem> getItems() {
		Collections.sort(this.order);
		return this.order;
	}

	/**
	 * Did I say I wasn't a fan of arrays?
	 * @return arbitrary array of items
	 */
	public Object[] getUnsafeItems() {
		Collections.sort(this.order);
		return this.order.toArray();
	}
	
	private boolean validateOrderPriorToCreation(OrderItem[] orderItems) {
		// TODO - implement for security
		boolean retVal = true;
		return retVal;
	}
	
	private boolean validateOrderPriorToCreation(List<OrderItem> orderItems) {
		// TODO - implement for security
		boolean retVal = true;
		return retVal;
	}
}
