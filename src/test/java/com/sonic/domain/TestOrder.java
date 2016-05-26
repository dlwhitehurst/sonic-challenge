package com.sonic.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestOrder {

	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Creates order using array of OrderItem constructor
	 * @return preloaded order with a material item and service item
	 */
	private Order createOrderUsingArray() {

		Item item1 = new Item((30L), "Rocks","0.10");
		Item item2 = new Item((32L), "One Car Wash", "5.50");
		
		MaterialOrderItem moi = new MaterialOrderItem(item1, 20);
		ServiceOrderItem soi = new ServiceOrderItem(item2, 4);
		
		OrderItem[] anArray = new OrderItem[2];
		anArray[0] = moi;
		anArray[1] = soi;
		
		Order order = new Order(anArray);
		
		return order;
	}
	
	/**
	 * Creates order using type-safe List of OrderItem(s)
	 * @return preload order with a material item and service item
	 */
	private Order createOrderUsingList() {

		Item item1 = new Item((424444444L), "Dirt","0.10");
		Item item2 = new Item((34693564L), "Consultation", "105.99");
		
		MaterialOrderItem moi1 = new MaterialOrderItem(item1, 5);
		ServiceOrderItem soi1 = new ServiceOrderItem(item2, 9);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		items.add(moi1);
		items.add(soi1);
		
		Order order = new Order(items);
		
		return order;
	}
	
	private Order createLargeOrder() {

		// items
		Item item1 = new Item(1L, "Dog","1.45");
		Item item2 = new Item(2L, "Cat", "2.99");
		Item item3 = new Item(3L, "Ardvark", "3.99");
		Item item4 = new Item(4L, "Apple", "2.99");
		Item item5 = new Item(5L, "Aeone", "5.99");
		Item item6 = new Item(6L, "Horse", "6.99");
		Item item7 = new Item(7L, "Caterpillar", "1.99");
		
		// order items
		MaterialOrderItem m1 = new MaterialOrderItem(item1, 1);
		MaterialOrderItem m2 = new MaterialOrderItem(item2, 1);
		MaterialOrderItem m3 = new MaterialOrderItem(item3, 1);
		MaterialOrderItem m4 = new MaterialOrderItem(item4, 1);
		MaterialOrderItem m5 = new MaterialOrderItem(item5, 1);
		MaterialOrderItem m6 = new MaterialOrderItem(item6, 1);
		MaterialOrderItem m7 = new MaterialOrderItem(item7, 1);
		
		// put together order
		List<OrderItem> items = new ArrayList<OrderItem>();

		items.add(m1);
		items.add(m2);
		items.add(m3);
		items.add(m4);
		items.add(m5);
		items.add(m6);
		items.add(m7);
		
		Order order = new Order(items);
		
		return order;
	}
	
	@Test
	public void testOrderCreation() {
		
		// data
		Order order = null;
		order = createOrderUsingArray();
		
		// validation
		assertNotNull(order);
		assertNotNull(order.getItems());
		assertEquals(order.getItems().size(),2);
		log.info(order.getItems().size()); 

		// data
		order = null;
		order = createOrderUsingList();
		
		// validation
		assertNotNull(order);
		assertNotNull(order.getItems());
		assertEquals(order.getItems().size(),2);
		log.info(order.toString()); 
		
	}

	@Test
	public void testOrderTotal() {

		// data
		Order order = createOrderUsingArray();
		BigDecimal total = order.getOrderTotal(new BigDecimal("0.07"));
		
		log.info(total.toString());
		
		// validation
		assertEquals(total.toString(),"24.1400");
	}

	@Test
	public void testStringOrderTotal() {

		// data
		Order order = createOrderUsingArray();
		log.info(order.getStringOrderTotal(new BigDecimal("0.07")));
		
		// validation
		assertEquals(order.getStringOrderTotal(new BigDecimal("0.07")),"$24.14");
	}

	@Test
	public void testGetListOrderItems() {

		// data
		Order order = createOrderUsingArray();
		List<OrderItem> items = order.getItems();
		
		// validation
		assertNotNull(items);
		assertEquals(items.size(),2);
	}
	
	@Test
	public void testGetArrayOrderItems() {
		
		// data
		Order order = createOrderUsingArray();
		Object[] items = new Object[2];
		items = order.getUnsafeItems();
		int count = 0;
		for (int i=0; i<2; i++) {
			Object obj = items[i];
			if (obj instanceof MaterialOrderItem) {
				count = 1;
				log.info("Found one MaterialOrderItem");
			}
		}
		
		// validation
		assertEquals(count,1);
	}
	
	@Test
	public void testSortedOrderItems() {
		Order order = createLargeOrder();
		List<OrderItem> orderItems = order.getItems();
		
		for (OrderItem orderItem: orderItems) {
			log.info(orderItem.getItem().getName());
		}
	}
}
