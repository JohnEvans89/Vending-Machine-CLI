package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class ChipTest {

	// A1
	@Test
	public void A1_Chip_Potato_Crisp_Test() {
		Chip chipChipChip = new Chip("Potato Crisps", new BigDecimal("3.05"));
		assertEquals("Crunch Crunch, Yum!", chipChipChip.getSlogan());
		assertEquals(5, chipChipChip.getQuantity());
		chipChipChip.dispenseVendingItem();
		assertEquals(4, chipChipChip.getQuantity());
	}

	// A2
	@Test
	public void A2_Chip_Stackers_Test() {
		Chip chipChipChip = new Chip("Stackers", new BigDecimal("1.45"));
		assertEquals("Crunch Crunch, Yum!", chipChipChip.getSlogan());
		assertEquals(5, chipChipChip.getQuantity());
		chipChipChip.dispenseVendingItem();
		assertEquals(4, chipChipChip.getQuantity());
	}

	// A3
	@Test
	public void A3_Chip_Grain_Waves_Test() {
		Chip chipChipChip = new Chip("Grain Waves", new BigDecimal("2.75"));
		assertEquals("Crunch Crunch, Yum!", chipChipChip.getSlogan());
		assertEquals(5, chipChipChip.getQuantity());
		chipChipChip.dispenseVendingItem();
		assertEquals(4, chipChipChip.getQuantity());
	}

	// A4
	@Test
	public void A4_Chip_Cloud_Popcorn_Test() {
		Chip chipChipChip = new Chip("Cloud Popcorn", new BigDecimal("3.65"));
		assertEquals("Crunch Crunch, Yum!", chipChipChip.getSlogan());
		assertEquals(5, chipChipChip.getQuantity());
		chipChipChip.dispenseVendingItem();
		assertEquals(4, chipChipChip.getQuantity());
	}

	/*
	 * @Test public void chipTestQuantity() { Chip chipChipChip = new
	 * Chip("Potato Crisps",new BigDecimal ("3.05"));
	 * assertEquals(5,chipChipChip.getQuantity()); }
	 * 
	 * @Test public void chipTestSetQuantity() { Chip chipChipChip = new
	 * Chip("Potato Crisps",new BigDecimal ("3.05"));
	 * chipChipChip.dispenseVendingItem();
	 * assertEquals(4,chipChipChip.getQuantity()); }
	 */
}
