/**
 *
 */
package com.sonic.domain;

/**
 * This class is an order item of type service (work offering) and is not
 * taxable.
 *
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class ServiceOrderItem extends OrderItem {

    /**
     * A unique serial class identifier.
     */
    private static final long serialVersionUID = -7947058873298639266L;

    /**
     * A Constructor.
     *
     * @param anItem
     *            final company item to be used with quantity to create the
     *            order item
     * @param aQuantity
     *            quantity of specific items for order
     */
    public ServiceOrderItem(final Item anItem, final Integer aQuantity) {
        this.setItem(anItem);
        this.setQuantity(aQuantity);
    }

    @Override
    public final boolean isTaxable() {
        return false;
    }
}
