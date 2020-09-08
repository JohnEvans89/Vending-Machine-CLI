package com.techelevator;

import java.math.BigDecimal;

public class Chip extends VendingItems {

	public Chip(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSlogan() {
		return "Crunch Crunch, Yum!";
	}

}
