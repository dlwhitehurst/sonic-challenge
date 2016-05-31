/**
 * Copyright (c) 2016 David L. Whitehurst and CI Wise Inc.
 *
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE.
 */

package com.sonic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sonic.common.Constants;

/**
 * This class is a composite object that abstracts the business item here called
 * an OrderItem. The object is a protected and persisted entity that is unique
 * only to its parent order. The order item, however needs to be flexible (allow
 * revision) but help provide quality of data prior to the creation of the
 * (final) order. The Item is unique but OrderItem uniqueness is not currently
 * enforced. A different kind of constraint is needed where:
 *
 * 1) Item keys should never be duplicated within an order regardless of name 2)
 * Multiple, valid items with different quantities should never occur.
 *
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public abstract class OrderItem extends BaseObject
        implements Comparable<OrderItem> {

    /**
     * A unique serial class identifier.
     */
    private static final long serialVersionUID = -5537067844211846861L;

    /**
     * The service offering or material object (cardinality=1 ONLY*).
     */
    private Item item;

    /**
     * Item objects are unique and Item-qty objects still may not be unique.
     * Establish a link to the Order. An Order contains 1...n OrderItems.
     */
    private Order parent;

    /**
     * How many of the unique item.
     */
    private Integer quantity;

    /**
     * This method should return boolean if the implementing object should be
     * taxed.
     *
     * @return taxable boolean
     */
    public abstract boolean isTaxable();

    /**
     * Get parent order object.
     *
     * @return theParentOrder
     */
    public final Order getParentOrder() {
        return this.parent;
    }

    /**
     * Get the specific item object.
     *
     * @return theItem
     */
    public final Item getItem() {
        return this.item;
    }

    /**
     * Get the quantity for the item.
     *
     * @return theQuantity
     */
    public final Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Setter for item.
     *
     * @param anItem
     *            item input argument
     */
    public final void setItem(final Item anItem) {
        this.item = anItem;
    }

    /**
     * Setter for parent.
     *
     * @param aParent
     *            parent order input argument
     */
    public final void setParentOrder(final Order aParent) {
        this.parent = aParent;
    }

    /**
     * Setter for quantity.
     *
     * @param aQuantity
     *            quantity input argument
     */
    public final void setQuantity(final Integer aQuantity) {
        this.quantity = aQuantity;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("item", this.getItem().toString())
                .append("quantity", this.getQuantity().toString()).toString();
    }

    @Override
    public final boolean equals(final Object o) {
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
     * Hashcode implementation based on Java type mixins.
     * @return integer hash code
     */
    @Override
    public final int hashCode() {
        int result;
        result = (this.parent != null ? this.parent.hashCode() : 0);
        result = Constants.MAGIC_HASHCODE_FACTOR * result
                + (this.item != null ? this.item.hashCode() : 0);
        result = Constants.MAGIC_HASHCODE_FACTOR * result
                + (this.quantity != null ? this.quantity.hashCode() : 0);
        return result;
    }

    /**
     * Method to compare or alphabetize order items according to
     * their item names.
     * @param orderItem theOrderItem to compare with this
     * @return integer value used for ordering
     */
    public final int compareTo(final OrderItem orderItem) {
        int nameCmp = this.item.getName()
                .compareTo(orderItem.getItem().getName());
        return (nameCmp != 0 ? nameCmp : 0);
    }

}
