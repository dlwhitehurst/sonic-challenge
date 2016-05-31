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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sonic.common.Constants;
import com.sonic.common.Security;
import com.sonic.domain.util.CurrencyUtil;

/**
 * This class represents the client's order which can consist of service or
 * material orderitems (item-qty).
 *
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public final class Order extends BaseObject implements Serializable, Security {

    /**
     * A unique serial class identifier.
     */
    private static final long serialVersionUID = -34235368252043602L;

    /**
     * The immutable order.
     */
    private final List<OrderItem> order;

    /**
     * We might restrict the order size to less than static Integer.MAX_VALUE.
     */
    private static final Integer MAX_ORDER_SIZE = 1000;

    /**
     * This prevents default construction.
     */
    @SuppressWarnings("unused")
    private Order() {
        this.order = null;
    }

    /**
     * I would probably take argument with the architect here for the use of an
     * array, so I'm accepting the constructor for now.
     *
     * @param theOrder
     *            array of order items
     */
    public Order(final OrderItem[] theOrder) {

        if (!isSecure()) {
            throw new RuntimeException("Class is not secure for construction and use.");
        }

        if (theOrder == null || theOrder.length == 0
                || theOrder.length > MAX_ORDER_SIZE) {
            throw new RuntimeException();
        }

        if (validateOrderPriorToCreation(theOrder)) {
            List<OrderItem> tmp = new ArrayList<OrderItem>();
            for (int i = 0; i < theOrder.length; i++) {
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
     * The preferred constructor.
     *
     * @param theOrder
     *            list of order items
     */
    public Order(final List<OrderItem> theOrder) {

        if (theOrder == null || theOrder.isEmpty()
                || theOrder.size() > MAX_ORDER_SIZE) {
            throw new RuntimeException();
        }

        if (validateOrderPriorToCreation(theOrder)) {
            this.order = theOrder;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * This method returns an accurate total based on tax Rate given.
     *
     * @param taxRate
     *            input for taxable items
     * @return numeric order total
     */
    public BigDecimal getOrderTotal(final BigDecimal taxRate) {

        BigDecimal total = new BigDecimal("0.00");
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);

        for (OrderItem orderItem : order) {
            if (orderItem.isTaxable()) {
                BigDecimal orderItemPrice = orderItem.getItem().getPrice()
                        .multiply(new BigDecimal(orderItem.getQuantity()));
                BigDecimal additionalTax = orderItemPrice.multiply(taxRate);
                total = total.add(orderItemPrice).add(additionalTax);
            } else {
                BigDecimal orderItemPrice = orderItem.getItem().getPrice()
                        .multiply(new BigDecimal(orderItem.getQuantity()));
                total = total.add(orderItemPrice);
            }
        }
        return total;
    }

    /**
     * This method returns a currency formatted string order total.
     *
     * @param taxRate
     *            input for taxable items
     * @return string representation of order total
     */
    public String getStringOrderTotal(final BigDecimal taxRate) {
        String total = CurrencyUtil
                .getBigDecimalCurrencyString(getOrderTotal(taxRate));
        return total;
    }

    /**
     * This returns a sorted list of only OrderItems (type-checked).
     *
     * @return type-safe list of order items
     */
    public List<OrderItem> getOrderItems() {
        Collections.sort(this.order);
        return this.order;
    }

    /**
     * Get array of Items (Object(requires Cast)).
     *
     * @return arbitrary array of Objects
     */
    public Object[] getOrderItemArray() {
        Collections.sort(this.order);
        return this.order.toArray();
    }

    /**
     * This private method validates data structure argument prior to object
     * construction.
     *
     * @param orderItems
     *            array of order items
     * @return true/false validation operation
     */
    private boolean validateOrderPriorToCreation(final OrderItem[] orderItems) {
        boolean retVal = true;
        for (int i = 0; i < orderItems.length; i++) {
            // inspect our private list before it's immutable
            if (orderItems[i] instanceof OrderItem) {
                // okay, save this orderItem and compare against the others
                for (int j = 0; j < orderItems.length; j++) {
                    if (i != j) {
                        if (orderItems[i].equals(orderItems[j])) {
                            // complete duplicate
                            retVal = false;
                        }
                        if (orderItems[i].getItem().getKey()
                                .longValue() == orderItems[j].getItem().getKey()
                                        .longValue()) {
                            /**
                             * Duplicate item keys, possible need to combine
                             * into single OrderItem, adding quantities. This is
                             * really a code issue
                             */
                            retVal = false;
                        }
                    }
                }
            }
        }
        return retVal;
    }

    /**
     * This private method validate data structure argument prior to object
     * construction.
     *
     * @param orderItems
     *            list of order items
     * @return true/false validation operation
     */
    private boolean validateOrderPriorToCreation(
            final List<OrderItem> orderItems) {
        boolean retVal = true;
        for (OrderItem orderItem : orderItems) {
            // inspect our private list before it's immutable
            if (orderItem instanceof OrderItem) {
                // okay, save this orderItem and compare against the others
                for (OrderItem innerOrderItem : orderItems) {
                    if (!orderItem.equals(innerOrderItem)) {
                        if (orderItem.getItem().getKey()
                                .longValue() == innerOrderItem.getItem()
                                        .getKey().longValue()) {
                            /**
                             * Duplicate item keys, possible need to combine
                             * into single OrderItem, adding quantities. This is
                             * really a code issue
                             */
                            retVal = false;
                        }
                    }
                }
            }
        }

        return retVal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("order", this.order.toString())
                .append("size", this.order.size()).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }

        final Order tmp = (Order) o;

        return this.hashCode() == tmp.hashCode();
    }

    @Override
    public int hashCode() {
        int result;
        if (this.order != null) {
          result = Constants.MAGIC_HASHCODE_FACTOR * this.order.hashCode();
        } else {
            result = 0;
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.sonic.common.Security#isSecure()
     */
    public boolean isSecure() {
        return true;
    }

}
