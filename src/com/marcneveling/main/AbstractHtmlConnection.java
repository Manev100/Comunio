package com.marcneveling.main;

import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public abstract class AbstractHtmlConnection {
	protected WebClient webClient;
	
	public AbstractHtmlConnection() {
		initialize();
	}
	
	private void initialize() {
		webClient = new WebClient(BrowserVersion.FIREFOX_17);
		checkBrowserLanguage();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setPrintContentOnFailingStatusCode(false);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
	}
	
	private void checkBrowserLanguage() {
		if(!webClient.getBrowserVersion().getBrowserLanguage().equals("en-us")){
			webClient.getBrowserVersion().setBrowserLanguage("en-us");
		}
		
	}

	protected WebClient getWebClient(){
		return webClient;
	}

}
