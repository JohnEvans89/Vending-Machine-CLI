package com.techelevator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class VendingMachineTest {
	private BigDecimal balance;
	private Map<String, List<VendingItems>> inventory = new LinkedHashMap<>();
	private String format = "%-4s%-20s$ %-8s%-2s%-20s%n";
	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	LocalDateTime time = LocalDateTime.now();
	DecimalFormat df = new DecimalFormat("$ #,##0.00");
	

	
	@Test
	public void feedMoney_and_getBalanceTest() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(1.00));
		assertEquals(new BigDecimal(1.00).setScale(2, BigDecimal.ROUND_HALF_UP), vend.getBalance());

	}

	@Test
	public void feedNegativeBill_and_getBalance0Test() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(-1));
		assertEquals(new BigDecimal(0), vend.getBalance());

	}

	@Test
	public void feedMoney1_and_Dispense_and_get4000Test() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(1.00));
		Integer[] expected = new Integer[] { 4, 0, 0, 0 };
		assertArrayEquals(expected, vend.dispenseChange());

	}

	@Test
	public void feedMoney68cents_and_Dispense_and_get2113Test() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(0.68));
		Integer[] expected = new Integer[] { 2, 1, 1, 3 };
		assertArrayEquals(expected, vend.dispenseChange());

	}
	
	@Test
	public void feedMoney415cents_and_Dispense_and_get16110Test() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(4.15));
		Integer[] expected = new Integer[] { 16, 1, 1, 0 };
		assertArrayEquals(expected, vend.dispenseChange());

	}
	
	@Test
	public void printChange415cents_and_get1611_PrintedTest() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(4.15));
		String expected = "Your change is: %n" + "16 quarters%n" + "1 dime%n" + "1 nickel%n";
		assertTrue(vend.printChange(vend.dispenseChange()).equals(expected));
	}
	
	@Test
	public void printChange73cents_and_get2203_PrintedTest() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(.73));
		String expected = "Your change is: %n" + "2 quarters%n" + "2 dimes%n" + "We have no pennies to return, your 3 pennies will be donated to a \"Good Cause\". %n";
		assertTrue(vend.printChange(vend.dispenseChange()).equals(expected));
	}
	
	@Test
	public void printChange0cents_and_getNoChange_PrintedTest() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(0.0));
		String expected = "Thank You for Your Business, Have a Great Day";
		assertTrue(vend.printChange(vend.dispenseChange()).equals(expected));
	}
	
	@Test
	public void printChange25cents_and_getQuarter_PrintedTest() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(0.25));
		String expected = "Your change is: %n" + "1 quarter%n";
		assertTrue(vend.printChange(vend.dispenseChange()).equals(expected));
	}
	
	@Test
	public void printChange1cent_and_getPenny_PrintedTest() {
		VendingMachine vend = new VendingMachine(inventory);
		vend.feedMoney(new BigDecimal(0.01));
		String expected = "Your change is: %n" + "We have no pennies to return, your penny will be donated to a \"Good Cause\". %n";
		assertTrue(vend.printChange(vend.dispenseChange()).equals(expected));
	}
	
	@Test
	public void selectInventoryA1with5BalanceGetItemAnd1_95Balance() throws FileNotFoundException {
		InventoryLoader loader = new InventoryLoader();
		VendingMachine vend = new VendingMachine(loader.fileImporter());
		vend.feedMoney(new BigDecimal(5.00));
		vend.dispenseItem("A1");
		
		BigDecimal expected = new BigDecimal(1.95).setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(expected, vend.getBalance());
	}
	
	
	@Test
	public void selectInventoryC1andD4with5BalanceGetItemsAnd3Balance() throws FileNotFoundException {
		InventoryLoader loader = new InventoryLoader();
		VendingMachine vend = new VendingMachine(loader.fileImporter());
		vend.feedMoney(new BigDecimal(5.00));
		vend.dispenseItem("C1");
		vend.dispenseItem("D4");
		
		BigDecimal expected = new BigDecimal(3.00).setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(expected, vend.getBalance());
	}
	
}
