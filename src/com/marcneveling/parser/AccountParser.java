package com.marcneveling.parser;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.marcneveling.beans.Account;
import com.marcneveling.main.AbstractHtmlConnection;

public class AccountParser extends AbstractHtmlConnection {
	private Account account;
	private HtmlPage loggedIn;
	
	public AccountParser(Account account) {
		super();
		this.account = account;
		parseAccountInformation();
	}
	
	private void parseAccountInformation() {
		try {
			webClient.openWindow(null, "Comunio");
			HtmlPage page = webClient.getPage("http://www.comunio.de");
			HtmlForm form = page.getFormByName("login");
			HtmlTextInput user = form.getInputByName("login");
			HtmlPasswordInput pass = form.getInputByName("pass");
	
			HtmlSubmitInput button = form.getInputByValue(">> Login");

			user.setValueAttribute(account.getUsername());
			pass.setValueAttribute(account.getPassword());
			loggedIn = button.click();
			isLoggedIn();
			String [] community = parseCommunity();
			account.setCommunity(community[0]);
			account.setCommunityId(community[1]);
			account.setUserId(parseUserId());
			account.setBalance(parseBalance());
			account.setTeamValue(parseTeamValue());
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		} finally{
			webClient.closeAllWindows();
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
	
	
	private String parseUserId() {
		HtmlDivision div = (HtmlDivision) loggedIn.getByXPath("//div[@id='userid']").get(0);
		return div.asText().split("DE")[1];
	}
	
	// return string with [0] = community name and [1] community id
	private String[] parseCommunity() {
		String[] community = new String[2];
		HtmlDivision div = (HtmlDivision) loggedIn.getByXPath("//div[@id='community_name']").get(0);
	
		HtmlAnchor link = (HtmlAnchor) div.getElementsByTagName("a").get(0);
		community[0] = link.getTextContent();
		
		String ref = link.getHrefAttribute();
		community[1] = ref.split("tid=")[1];
		return community;
	}
	
	private String parseBalance() {
		HtmlDivision div = (HtmlDivision) loggedIn.getByXPath("//div[@id='userbudget']").get(0);
		return div.asText().split(" ")[2];
	}

	private String parseTeamValue() {
		HtmlDivision div = (HtmlDivision) loggedIn.getByXPath("//div[@id='teamvalue']").get(0);
		return div.asText().trim().split(" ")[2];
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void refreshAccountInformation(){
		parseAccountInformation();
	}
	
}
