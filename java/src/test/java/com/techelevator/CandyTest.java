package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class CandyTest {

	// B1
	@Test
	public void B1_Candy_Moonpie_Test() {
		Candy cancan = new Candy("Moonpie", new BigDecimal("1.80"));
		assertEquals("Munch Munch, Yum!", cancan.getSlogan());
		assertEquals(5, cancan.getQuantity());
		cancan.dispenseVendingItem();
		assertEquals(4, cancan.getQuantity());

	}

	// B2
	@Test
	public void B2_Candy_Cowtales_Test() {
		Candy cancan = new Candy("Cowtales", new BigDecimal("1.50"));
		assertEquals("Munch Munch, Yum!", cancan.getSlogan());
		assertEquals(5, cancan.getQuantity());
		cancan.dispenseVendingItem();
		assertEquals(4, cancan.getQuantity());
	}

	// B3
	@Test
	public void B3_Candy_Wonka_Bar_Test() {
		Candy cancan = new Candy("Wonka Bar", new BigDecimal("1.50"));
		assertEquals("Munch Munch, Yum!", cancan.getSlogan());
		assertEquals(5, cancan.getQuantity());
		cancan.dispenseVendingItem();
		assertEquals(4, cancan.getQuantity());
	}

	// B4
	@Test
	public void B4_Candy_Crunchie_Test() {
		Candy cancan = new Candy("Crunchie", new BigDecimal("1.75"));
		assertEquals("Munch Munch, Yum!", cancan.getSlogan());
		assertEquals(5, cancan.getQuantity());
		cancan.dispenseVendingItem();
		assertEquals(4, cancan.getQuantity());
	}
	
	@Test
	public void B4_Candy_Crunchie_dispenseTo_0() {
		Candy cancan = new Candy("Crunchie", new BigDecimal("1.75"));
		assertEquals(5, cancan.getQuantity());
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		assertEquals(0, cancan.getQuantity());
	}
	
	@Test
	public void B4_Candy_Crunchie_dispensePast_0() {
		Candy cancan = new Candy("Crunchie", new BigDecimal("1.75"));
		assertEquals(5, cancan.getQuantity());
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		cancan.dispenseVendingItem();
		assertEquals(0, cancan.getQuantity());
	}

	/*
	 * @Test public void candyTestQuantity() { Candy cancan = new
	 * Candy("Moonpie",new BigDecimal ("1.80"));
	 * assertEquals(5,cancan.getQuantity()); }
	 * 
	 * @Test public void candyTestSetQuantity() { Candy cancan = new
	 * Candy("Moonpie",new BigDecimal ("1.80")); cancan.dispenseVendingItem();
	 * assertEquals(4,cancan.getQuantity()); }
	 */

}
