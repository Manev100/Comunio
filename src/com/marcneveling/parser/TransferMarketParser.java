package com.marcneveling.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.marcneveling.beans.PlayerOnMarket;
import com.marcneveling.main.AbstractHtmlConnection;

public class TransferMarketParser extends AbstractHtmlConnection {
	
	public TransferMarketParser(){
		super();
	}
	
	public LinkedList<PlayerOnMarket> getTransferMarket(String id) {
		try {
			HtmlPage page = webClient.getPage("http://www.comunio.de/teamInfo.phtml?tid=" + id);
			HtmlTable table = (HtmlTable) page.getByXPath("//div[@id='contentfullsizeib']//table[@class='tablecontent03']").get(0);
		} catch (FailingHttpStatusCodeException | IOException e) {
			System.out.println("Could not parse transfer market!");
			e.printStackTrace();
		}
		return null;
	}


}
