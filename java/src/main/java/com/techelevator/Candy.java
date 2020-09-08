package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingItems {

	public Candy(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSlogan() {
		return "Munch Munch, Yum!";
	}

}
