package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingItems {

	private String name;
	private BigDecimal price;
	private int quantity;
	
	public VendingItems(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
		this.quantity = 5;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean dispenseVendingItem() {
		if (this.quantity > 0) {
			this.quantity -= 1;
		} else {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public abstract String getSlogan();
		
	
	
	
		


}
