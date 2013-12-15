package com.marcneveling.beans;

import java.util.LinkedList;

public class User {
	
	private String name;
	private String nick;
	private String userId;
	private String community;
	private String communityId;
	private int points;
	private LinkedList<Player> team;
	private LinkedList<Player> starters;
	
	public User(String userId) {
		this.userId = userId;
		team = new LinkedList<Player>();
		setStarters(new LinkedList<Player>());
		name = "";
		nick = "";
		userId = "";
		community = "";
		communityId = "";
		points = 0;
	}
	
	
	public LinkedList<Player> getTeam() {
		return team;
	}

	public void setTeam(LinkedList<Player> team) {
		this.team = team;
		if(team.size() >= 11){
			starters = new LinkedList<Player>(team.subList(0, 11));
		}else{
			starters = new LinkedList<Player>(team.subList(0, team.size()));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public String toString(){
		return "Name: " + name + " Nick: " + nick + " ID: " + userId + " Community: " + community + " CommunityID: " + communityId + " Points: " + points + " Team: " ;
	}


	public LinkedList<Player> getStarters() {
		return starters;
	}


	public void setStarters(LinkedList<Player> starters) {
		this.starters = starters;
	}

}
