package com.marcneveling.main;

public enum Positions {
	STRIKER(4, "striker"), MIDFIELDER(3, "midfielder"), DEFENDER(2, "defender"), GOALKEEPER(1, "goalkeeper"), UNKNOWN(0, "unknown"); 
	
	private int number;
	private String position;
	Positions(int num, String position){
		this.number = num;
		this.position = position;
	}
	
	public int getNumber() {
		return number;
	}
	public String getPosition() {
		return position;
	}
}
