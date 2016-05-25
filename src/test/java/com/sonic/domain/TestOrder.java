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
	
	@Test
	public void testOrderCreation() {
		Item item1 = new Item((30L), "Rocks","0.10");
		Item item2 = new Item((32L), "One Car Wash", "5.50");
		MaterialOrderItem moi = new MaterialOrderItem(item1, 20);
		ServiceOrderItem soi = new ServiceOrderItem(item2, 4);
		OrderItem[] anArray = new OrderItem[2];
		anArray[0] = moi;
		anArray[1] = soi;
		Order order = new Order(anArray);
		
		assertNotNull(order);
		assertNotNull(order.getItems());
		assertEquals(order.getItems().size(),2);
		log.info(order.getItems().size()); 

		Item item3 = new Item((424444444L), "Dirt","0.10");
		Item item4 = new Item((34693564L), "Consultation", "105.99");
		MaterialOrderItem moi2 = new MaterialOrderItem(item3, 5);
		ServiceOrderItem soi3 = new ServiceOrderItem(item4, 9);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		items.add(moi2);
		items.add(soi3);
		Order order2 = new Order(items);
		assertNotNull(order2);
		assertNotNull(order2.getItems());
		assertEquals(order2.getItems().size(),2);
		log.info(order2.toString()); 
		
	}

	@Test
	public void testOrderTotal() {
		Item item1 = new Item((30L), "Rocks","0.10");
		Item item2 = new Item((32L), "One Car Wash", "5.50");
		MaterialOrderItem moi = new MaterialOrderItem(item1, 20);
		ServiceOrderItem soi = new ServiceOrderItem(item2, 4);
		OrderItem[] anArray = new OrderItem[2];
		anArray[0] = moi;
		anArray[1] = soi;
		Order order = new Order(anArray);
		BigDecimal total = order.getOrderTotal(new BigDecimal("0.07"));
		log.info(total.toString());
		assertEquals(total.toString(),"24.1400");
	}

	@Test
	public void testStringOrderTotal() {
		Item item1 = new Item((30L), "Rocks","0.10");
		Item item2 = new Item((32L), "One Car Wash", "5.50");
		MaterialOrderItem moi = new MaterialOrderItem(item1, 20);
		ServiceOrderItem soi = new ServiceOrderItem(item2, 4);
		OrderItem[] anArray = new OrderItem[2];
		anArray[0] = moi;
		anArray[1] = soi;
		Order order = new Order(anArray);
		log.info(order.getStringOrderTotal(new BigDecimal("0.07")));
		assertEquals(order.getStringOrderTotal(new BigDecimal("0.07")),"$24.14");
	}

	@Test
	public void testGetListOrderItems() {
		Item item1 = new Item((30L), "Rocks","0.10");
		Item item2 = new Item((32L), "One Car Wash", "5.50");
		MaterialOrderItem moi = new MaterialOrderItem(item1, 20);
		ServiceOrderItem soi = new ServiceOrderItem(item2, 4);
		OrderItem[] anArray = new OrderItem[2];
		anArray[0] = moi;
		anArray[1] = soi;
		Order order = new Order(anArray);
		List<OrderItem> items = order.getItems();
		assertNotNull(items);
		assertEquals(items.size(),2);
	}
	
	@Test
	public void testGetArrayOrderItems() {
		Item item1 = new Item((30L), "Rocks","0.10");
		Item item2 = new Item((32L), "One Car Wash", "5.50");
		MaterialOrderItem moi = new MaterialOrderItem(item1, 20);
		ServiceOrderItem soi = new ServiceOrderItem(item2, 4);
		OrderItem[] anArray = new OrderItem[2];
		anArray[0] = moi;
		anArray[1] = soi;
		Order order = new Order(anArray);
		Object[] items = new Object[2];
		items = order.getUnsafeItems();
		for (int i=0; i<2; i++) {
			Object obj = items[i];
			if (obj instanceof MaterialOrderItem) {
				log.info("Found one MaterialOrderItem");
			}
		}
	}

}
