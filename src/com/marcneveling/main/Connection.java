package com.marcneveling.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.marcneveling.beans.Account;


public class Connection {
	private Account account;
	private HtmlPage loggedIn;
	private WebClient webClient;
	
	public Connection(Account account) {
		this.account = account;
		initialize();
		
	}

	private void initialize() {
		webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setPrintContentOnFailingStatusCode(false);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
		
	}
	
	public String[][] getTransferMarket(){
		HtmlTable table = null;
		try {
			HtmlAnchor link = loggedIn.getAnchorByHref("exchangemarket.phtml");
			HtmlPage market = link.click();
			saveToFile("transfermarket.txt", market.asXml());
			table = market.getHtmlElementById("searchTextResults");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tableToArray(table);
	}
	
	
	public String[][] getStandingLastMatchDay(){
		HtmlTable table = null;
		try {
			HtmlAnchor link = loggedIn.getAnchorByHref("standings.phtml?currentweekonly_x=22");
			HtmlPage market = link.click();
			saveToFile("lastmatch.txt", market.asXml());
			table = market.getHtmlElementById("tablestandings");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tableToArray(table);
	}
	
	
	public String[][] getStanding(){
		HtmlTable table = null;
		try {
			HtmlAnchor link = loggedIn.getAnchorByHref("standings.phtml");
			HtmlPage market = link.click();
			saveToFile("standings.txt", market.asXml());
			table = market.getHtmlElementById("tablestandings");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tableToArray(table);
	}

	public String[] getLineUp(){
		try {
			HtmlAnchor link = loggedIn.getAnchorByHref("lineup.phtml");
			HtmlPage market = link.click();
			saveToFile("lineup.txt", market.asXml());
			List<?> tables = market.getByXPath("//table");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String[] getAllPlayers(){
		List<String[]> players = new LinkedList<String[]>();
		try {
			HtmlAnchor link = loggedIn.getAnchorByHref("lineup.phtml");
			HtmlPage market = link.click();
			saveToFile("players.txt", market.asText());

			List<?> tables = market.getByXPath("//table");
			getAttackers((HtmlTable) tables.get(4));
			getMidfielders((HtmlTable) tables.get(5));
			getDefenders((HtmlTable) tables.get(6));
			getGoalies((HtmlTable) tables.get(7));
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return null;
	}

	private String[][] getGoalies(HtmlTable htmlTable) {
		return null;
	}

	private void getDefenders(HtmlTable htmlTable) {
		// TODO Auto-generated method stub
		
	}

	private void getMidfielders(HtmlTable htmlTable) {
		// TODO Auto-generated method stub
		
	}

	private void getAttackers(HtmlTable htmlTable) {
		// TODO Auto-generated method stub
		
	}
	private void saveToFile(String filename, String content){
		PrintWriter out;
		try {
			out = new PrintWriter(filename);
			out.println(content);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean isLoggedIn(){
		try{
			for(HtmlAnchor link:loggedIn.getAnchors()){
				if(link.getTextContent().trim().equalsIgnoreCase(account.getUsername())){
					System.out.println("You are logged in");
					return true;
				}
			}
			System.out.println("login failed");
			return false;
		}catch(Exception e){
			System.out.println("login failed");
			return false;
		}
	}
	
	private String[][] tableToArray(HtmlTable table){
		System.out.println(table.asText());
		LinkedList<String[]> rows = new LinkedList<String[]>();
		for (final HtmlTableRow row : table.getRows()) {
		    LinkedList<String> cells = new LinkedList<String>();
		    for (final HtmlTableCell cell : row.getCells()) {
		    	cells.add(cell.asText());
		    }
		    rows.add(cells.toArray(new String[0]));
		}
		String[][] content = new String[rows.size()][];
		for (int i = 0; i < content.length; i++) {
			content[i] = rows.get(i);
		}
		return content;	
	}
	
}
