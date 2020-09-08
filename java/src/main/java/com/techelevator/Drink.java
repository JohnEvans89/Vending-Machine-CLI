package com.techelevator;

import java.math.BigDecimal;

public class Drink extends VendingItems {

	public Drink(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSlogan() {
		return "Glug Glug, Yum!";
	}

}
