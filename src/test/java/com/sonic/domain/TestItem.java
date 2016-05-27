package com.sonic.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestItem {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Test
	public void testItemDefaultConstruction() {
	    fail("Can't create default constructor because it's private now.");
	}
	
	@Test
	public void testItemInstancing() {
		Item item = new Item(new Long(500), "Widget", "57.50");
		
		assertNotNull(item);
		assertNotNull(item.getKey());
		assertNotNull(item.getName());
		assertNotNull(item.getPrice());
		
		assertEquals(item.getKey(), new Long(500));
		assertEquals(item.getName(), "Widget");
		assertEquals(item.getPrice(), new BigDecimal("57.50"));
		
		
	}
	
	@Test
	public void testHashCode() {
		Item item = new Item(new Long(500), "Widget", "57.50");
		assertEquals(item.hashCode(),2118982708);
	}
	
	@Test
	public void testEquals() {
		Item item1 = new Item(new Long(500), "Widget1", "57.50");
		Item item2 = new Item(new Long(501), "Widget2", "75.25");
		
		boolean tmp = item1.equals(item2);
		
		assertFalse(tmp);
	}
	
	@Test
	public void testToString() {
		Item item = new Item(new Long(500), "Widget", "57.50");
		String tmp = item.toString();
		assertNotNull(tmp);
		log.info(item.toString());
	}
}
