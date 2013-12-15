package com.marcneveling.beans;

import java.util.LinkedList;

public class Community {
	private String id;
	private String name;
	private Standing standing;
	private LinkedList<PlayerOnMarket> transferMarket;
	
	public Community(String id){
		this.id = id;
		this.name = "";
		transferMarket = new LinkedList<PlayerOnMarket>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Standing getStanding() {
		return standing;
	}

	public void setStanding(Standing standing) {
		this.standing = standing;
	}

	public LinkedList<PlayerOnMarket> getTransferMarket() {
		return transferMarket;
	}

	public void setTransferMarket(LinkedList<PlayerOnMarket> transferMarket) {
		this.transferMarket = transferMarket;
	}
	
	public String toString(){
		return id + " " + name + "\n" + standing.toString() + "\n" + transferMarket.toString();
	}
}
