package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingItems {

	public Gum(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSlogan() {
		return "Chew Chew, Yum!";
	}

}
