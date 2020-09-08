package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class GumTest {

	// D1
		@Test
		public void D1_Gum_Cola_Test() {
			Gum chew = new Gum("Cola", new BigDecimal("1.25"));
			assertEquals("Chew Chew, Yum!", chew.getSlogan());
			assertEquals(5, chew.getQuantity());
			chew.dispenseVendingItem();
			assertEquals(4, chew.getQuantity());

		}

		// D2
		@Test
		public void D2_Gum_Dr_Salt_Test() {
			Gum chew = new Gum("Dr. Salt", new BigDecimal("1.50"));
			assertEquals("Chew Chew, Yum!", chew.getSlogan());
			assertEquals(5, chew.getQuantity());
			chew.dispenseVendingItem();
			assertEquals(4, chew.getQuantity());
		}

		// D3
		@Test
		public void D3_Gum_Mountain_Melter_Test() {
			Gum chew = new Gum("Mountain Melter", new BigDecimal("1.50"));
			assertEquals("Chew Chew, Yum!", chew.getSlogan());
			assertEquals(5, chew.getQuantity());
			chew.dispenseVendingItem();
			assertEquals(4, chew.getQuantity());
		}

		// D4
		@Test
		public void D4_Gum_Heavy_Test() {
			Gum chew = new Gum("Heavy", new BigDecimal("1.50"));
			assertEquals("Chew Chew, Yum!", chew.getSlogan());
			assertEquals(5, chew.getQuantity());
			chew.dispenseVendingItem();
			assertEquals(4, chew.getQuantity());
		}

}
