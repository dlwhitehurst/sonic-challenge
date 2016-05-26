package com.sonic.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestOrderItems {

	@Test
	public void testTaxable() {
		Item item1 = new Item(1L, "Dog", "0.00");
		Item item2 = new Item(2L, "Cat", "0.00");

		MaterialOrderItem m = new MaterialOrderItem(item1, 1);
		ServiceOrderItem s = new ServiceOrderItem(item2, 1);
		
		assertTrue(m.isTaxable());
		assertFalse(s.isTaxable());
	}

}
