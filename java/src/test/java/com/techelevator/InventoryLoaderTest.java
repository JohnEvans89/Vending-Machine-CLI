package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class InventoryLoaderTest {
	InventoryLoader tester = new InventoryLoader();

	@Test
	public void testA1ReturnPotatoCrisps() throws FileNotFoundException {
		Map<String, List<VendingItems>> itemMap = tester.fileImporter();

		VendingItems chipItem = itemMap.get("A1").get(0);

		assertEquals("Potato Crisps", chipItem.getName());
		assertEquals(new BigDecimal("3.05"), chipItem.getPrice());
		assertEquals(true, itemMap.containsKey("A1"));
		assertEquals("Crunch Crunch, Yum!", chipItem.getSlogan());

	}
	
	@Test
	public void testB2ReturnCowtales() throws FileNotFoundException {
		Map<String, List<VendingItems>> itemMap = tester.fileImporter();

		VendingItems candyItem = itemMap.get("B2").get(0);

		assertEquals("Cowtales", candyItem.getName());
		assertEquals(new BigDecimal("1.50"), candyItem.getPrice());
		assertEquals(true, itemMap.containsKey("B2"));
		assertEquals("Munch Munch, Yum!", candyItem.getSlogan());

	}
	
	@Test
	public void testC2ReturnDrSalt() throws FileNotFoundException {
		Map<String, List<VendingItems>> itemMap = tester.fileImporter();

		VendingItems drinkItem = itemMap.get("C2").get(0);

		assertEquals("Dr. Salt", drinkItem.getName());
		assertEquals(new BigDecimal("1.50"), drinkItem.getPrice());
		assertEquals(true, itemMap.containsKey("C2"));
		assertEquals("Glug Glug, Yum!", drinkItem.getSlogan());

	}
	
	@Test
	public void testD2ReturnLittleLeagueChew() throws FileNotFoundException {
		Map<String, List<VendingItems>> itemMap = tester.fileImporter();

		VendingItems gumItem = itemMap.get("D2").get(0);

		assertEquals("Little League Chew", gumItem.getName());
		assertEquals(new BigDecimal("0.95"), gumItem.getPrice());
		assertEquals(true, itemMap.containsKey("D2"));
		assertEquals("Chew Chew, Yum!", gumItem.getSlogan());

	}

}
