package com.sonic.domain;

/**
 * This class represents an order item of type material (tangible item) and is
 * taxable.
 *
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class MaterialOrderItem extends OrderItem {

    /**
     * A unique serial class identifier.
     */
    private static final long serialVersionUID = -3623292236831612014L;

    /**
     * A Constructor.
     *
     * @param anItem
     *            company item
     * @param aQuantity
     *            how many
     */
    public MaterialOrderItem(final Item anItem, final Integer aQuantity) {
        this.setItem(anItem);
        this.setQuantity(aQuantity);
    }

    @Override
    public final boolean isTaxable() {
        return true;
    }
}
