/**
 * 
 */
package com.sonic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This class is a composite object that abstracts the business item here called
 * an OrderItem. The object is a protected and persisted entity that is unique only
 * to its parent order.
 *  
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public abstract class OrderItem extends BaseObject implements Comparable<OrderItem> {

	/**
	 * unique serial class identifier
	 */
	private static final long serialVersionUID = -5537067844211846861L;

	/**
	 * The service offering or material object (cardinality=1 ONLY*) 
	 */
	protected Item item;
	
	/**
	 * Item objects are unique and Item-qty objects still may not be unique. Establish a 
	 * link to the Order. An Order contains 1...n OrderItems
	 */
	protected Order parent;
	
	/**
	 * How many of the unique item
	 */
	protected Integer quantity;

	/**
	 * This method should return true if the implementing object should be taxed.
	 * @return taxable boolean
	 */
	public abstract boolean isTaxable();
	
	public Order getParentOrder() {
		return this.parent;
	}

	public Item getItem() {
		return this.item;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setItem(Item _item) {
		this.item = _item;
	}

	public void setParentOrder(Order _parent) {
		this.parent = _parent;
	}

	public void setQuantity(Integer _quantity) {
		this.quantity = _quantity;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("item", this.item.toString())
				.append("quantity", this.quantity.toString()).toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof OrderItem)) {
			return false;
		}

		final OrderItem tmp = (OrderItem) o;

		return this.hashCode() == tmp.hashCode();
	}
	
	/**
	 * Hashcode implementation based on Java type mixins
	 */
	@Override
	public int hashCode() {
		int result;
		result = (this.parent != null ? this.parent.hashCode() : 0);
		result = 29 * result + (this.item != null ? this.item.hashCode() : 0);
		result = 29 * result + (this.quantity != null ? this.quantity.hashCode() : 0);
		return result;
	}
	
	@Override
	public int compareTo(OrderItem orderItem) {
		int nameCmp = this.item.getName().compareTo(orderItem.getItem().getName());
		return (nameCmp != 0 ? nameCmp : 0);
	}
 
}
