package com.marcneveling.beans;

public class Account {
	

	private String username;
	private String password;
	private String userId;
	private String community;
	private String communityId;
	private String balance;
	private String teamValue;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getBalance() {
		return balance;
	}
	
	public String getTeamValue() {
		return teamValue;
	}

	public void setTeamValue(String teamValue) {
		this.teamValue = teamValue;
	}
	
	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String toString(){
		return username + " ID: " + userId + " Comm: " + community + " Comm-ID: " + communityId + " Balance: " + balance + " Value: " + teamValue;
	}

	
}
