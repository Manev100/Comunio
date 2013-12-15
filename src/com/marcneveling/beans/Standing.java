package com.marcneveling.beans;

import java.util.LinkedList;

public class Standing {
	private LinkedList<User> players;
	public Standing(LinkedList<User> players) {
		this.players = players;
	}
	
	public String toString(){
		return players.toString();
	}

}
