package com.marcneveling.beans;

public class PlayerOnMarket extends Player {
	private String price;
	
	public PlayerOnMarket(String name) {
		super(name);
		setPrice("");
		
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
