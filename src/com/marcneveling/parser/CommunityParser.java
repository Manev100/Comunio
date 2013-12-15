package com.marcneveling.parser;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.marcneveling.beans.Community;
import com.marcneveling.main.AbstractHtmlConnection;

public class CommunityParser extends AbstractHtmlConnection{
	private TransferMarketParser tmp;
	private StandingParser sp;
	public CommunityParser(){
		super();
		tmp = new TransferMarketParser();
		sp = new StandingParser();
	}
	
	public Community getCommunity(String id){
		Community com = new Community(id);
		com.setName(parseName(id));
		com.setTransferMarket(tmp.getTransferMarket(id));
		com.setStanding(sp.getStanding(id));
		return com;
	}

	private String parseName(String id) {
		try {
			HtmlPage page = webClient.getPage("http://www.comunio.de/teamInfo.phtml?tid=" + id);
			HtmlHeading1 heading = (HtmlHeading1) page.getByXPath("//div[@id='title']//h1").get(0);
			return heading.asText();
		} catch (FailingHttpStatusCodeException | IOException e) {
			System.out.println("Could not parse community-name");
			e.printStackTrace();
		}
		return null;
	}
}
