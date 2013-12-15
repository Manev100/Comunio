package com.marcneveling.parser;

import java.util.LinkedList;

import com.marcneveling.beans.Standing;
import com.marcneveling.beans.User;
import com.marcneveling.main.AbstractHtmlConnection;

public class StandingParser extends AbstractHtmlConnection {

	public Standing getStanding(String id) {
		LinkedList<User> standing = new LinkedList<User>();
		
		
		return new Standing(standing);
	}

}
