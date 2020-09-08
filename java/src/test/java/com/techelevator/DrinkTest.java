package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class DrinkTest {

	// C1
	@Test
	public void C1_Drink_Cola_Test() {
		Drink soda = new Drink("Cola", new BigDecimal("1.25"));
		assertEquals("Glug Glug, Yum!", soda.getSlogan());
		assertEquals(5, soda.getQuantity());
		soda.dispenseVendingItem();
		assertEquals(4, soda.getQuantity());

	}

	// C2
	@Test
	public void C2_Drink_Dr_Salt_Test() {
		Drink soda = new Drink("Dr. Salt", new BigDecimal("1.50"));
		assertEquals("Glug Glug, Yum!", soda.getSlogan());
		assertEquals(5, soda.getQuantity());
		soda.dispenseVendingItem();
		assertEquals(4, soda.getQuantity());
	}

	// C3
	@Test
	public void C3_Drink_Mountain_Melter_Test() {
		Drink soda = new Drink("Mountain Melter", new BigDecimal("1.50"));
		assertEquals("Glug Glug, Yum!", soda.getSlogan());
		assertEquals(5, soda.getQuantity());
		soda.dispenseVendingItem();
		assertEquals(4, soda.getQuantity());
	}

	// C4
	@Test
	public void C4_Drink_Heavy_Test() {
		Drink soda = new Drink("Heavy", new BigDecimal("1.50"));
		assertEquals("Glug Glug, Yum!", soda.getSlogan());
		assertEquals(5, soda.getQuantity());
		soda.dispenseVendingItem();
		assertEquals(4, soda.getQuantity());
	}

}
