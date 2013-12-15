package com.marcneveling.main;

import java.util.HashMap;

import com.marcneveling.beans.Account;
import com.marcneveling.parser.AccountParser;
import com.marcneveling.parser.CommunityParser;
import com.marcneveling.parser.UserParser;

public class Model {
	private Connection connection;
	private HashMap<String, String> playerToID;
	private String[][] standing;
	private Account user;
	private static final String username = "Gomez2FCB";
	private static final String password = "!avatar!";
	
	public Model() {
		user = new Account(username, password);
		AccountParser ap = new AccountParser(user);
		System.out.println(user.toString());
		
		UserParser up = new UserParser();
		CommunityParser cp = new CommunityParser();
		System.out.println(cp.getCommunity("1475076"));
		playerToID = new HashMap<String, String>();
		// transfermarket(Linked list of players, new class PlayerOnMarket with owner?), standing to do
	}

	public String[] getContent() {
		
		return connection.getLineUp();
	}
	public String[][] getStanding() {
		
		return standing;
	}
	
	public String[][] getStandingLastMatchDay() {
		
		return connection.getStandingLastMatchDay();
	}
	
	public String[][] getTransferMarket() {
		
		return connection.getTransferMarket();
	}
	
	public String[] getAllPlayers() {
		
		return connection.getAllPlayers();
	}

}
