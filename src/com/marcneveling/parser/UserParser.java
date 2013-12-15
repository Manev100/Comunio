package com.marcneveling.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.LinkedList;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.marcneveling.beans.Player;
import com.marcneveling.beans.User;
import com.marcneveling.main.AbstractHtmlConnection;
import com.marcneveling.main.Positions;

public class UserParser extends AbstractHtmlConnection {
	private HtmlPage userPage;
	
	public UserParser() {
		super();
	}
	
	public User getUser(String userId){
		User user = new User(userId);
		try {
			userPage = webClient.getPage("http://www.comunio.de/playerInfo.phtml?pid=" + userId);
		} catch (FailingHttpStatusCodeException e) {
			userPage = null;
			e.printStackTrace();
		} catch (MalformedURLException e) {
			userPage = null;
			e.printStackTrace();
		} catch (IOException e) {
			userPage = null;
			e.printStackTrace();
		}
		if(null != userPage){
			parseUser(user);
		}else{
			System.out.println("Could not parse User!");
		}
		return user;
	}

	private void parseUser(User user) {
		user.setCommunity(parseCommunity());
		user.setCommunityId(parseCommunityId());
		user.setName(parseName());
		user.setNick(parseNick());
		user.setPoints(parsePoints());
		user.setTeam(parseTeam());
		
	}
	
	// outputs list of all players in the team, with the starting eleven being the first 11 players
	private LinkedList<Player> parseTeam() {
		
		LinkedList<String> lineUp = new LinkedList<String>();
		for(int i = 0; i<4; i++){
			HtmlTable table = (HtmlTable) userPage.getByXPath("//div[@id='contentfullsizeib']//table//table").get(i);
			for(HtmlTableCell cell: table.getRow(1).getCells()){
				lineUp.add(cell.asText());
			}
		}

		LinkedList<Player> players = new LinkedList<Player>();
		HtmlTable table = (HtmlTable) userPage.getByXPath("//div[@id='contentfullsizeib']//table[@class='tablecontent03']").get(0);
		for(HtmlTableRow row: table.getRows()){
			if(row != table.getRows().get(0)){
				Player aPlayer = new Player(row.getCell(2).asText());
				aPlayer.setPoints(Integer.parseInt(row.getCell(5).asText()));
				aPlayer.setWorth(row.getCell(4).asText());
				aPlayer.setPosition(Positions.valueOf(row.getCell(6).asText().trim().toUpperCase()));
				HtmlSpan club = (HtmlSpan) row.getCell(3).getFirstChild();
				aPlayer.setClub(club.getAttribute("title"));
				
				// add to top of list when player is in starting 11 else add to the bottom
				if(lineUp.contains(aPlayer.getName())){
					players.addFirst(aPlayer);
				}else{
					players.addLast(aPlayer);
				}
			}
		}
		
		return players;
	}

	private int parsePoints() {
		HtmlTable table = (HtmlTable) userPage.getByXPath("//div[@id='contentfullsizeib']//div//table").get(0);
		String points = table.getCellAt(1, 0).asText().split(" ")[1].trim();
		return Integer.parseInt(points);
	}

	private String parseNick() {
		HtmlHeading1 heading = (HtmlHeading1) userPage.getByXPath("//div[@id='title']//h1").get(0);
		return heading.asText().split("[()]")[1].trim();
	}

	private String parseName() {
		HtmlHeading1 heading = (HtmlHeading1) userPage.getByXPath("//div[@id='title']//h1").get(0);
		return heading.asText().split("[()]")[0].trim();
	}

	private String parseCommunityId() {
		HtmlAnchor link = (HtmlAnchor) userPage.getByXPath("//div[@id='contentfullsizeib']//a").get(0);
		return link.getHrefAttribute().split("tid=")[1].trim();
	}

	private String parseCommunity() {
		HtmlAnchor link = (HtmlAnchor) userPage.getByXPath("//div[@id='contentfullsizeib']//a").get(0);
		return link.asText();
	}

}
