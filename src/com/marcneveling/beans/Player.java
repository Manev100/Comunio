package com.marcneveling.beans;

import com.marcneveling.main.Positions;

public class Player {
	
	private String name;
	private int points;
	private int pointsLastMatchDay;
	private String worth;
	private String owner;
	private String club;
	private Positions position;

	public Player(String name) {
		super();
		this.name = name;
		this.points = 0;
		this.pointsLastMatchDay = 0;
		this.worth = "";
		this.owner = "";
		this.club = "";
		this.position = Positions.UNKNOWN;
	}
	
	public String toString(){
		return name + " | position: " + position.getPosition() + " | points: " + points + " | value: " + worth +  " | club: " + club;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPointsLastMatchDay() {
		return pointsLastMatchDay;
	}
	public void setPointsLastMatchDay(int pointsLastMatchDay) {
		this.pointsLastMatchDay = pointsLastMatchDay;
	}
	public String getWorth() {
		return worth;
	}
	public void setWorth(String worth) {
		this.worth = worth;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public Positions getPosition() {
		return position;
	}

	public void setPosition(Positions position) {
		this.position = position;
	}
	
	
}
