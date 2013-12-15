package com.marcneveling.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class GUI extends JFrame{
	private Model model;
	
	public GUI(Model model) {
		super("Comunio cheater");
		this.model = model;
		JPanel mainPane = new JPanel();
		setContentPane(mainPane);
		
		JTable table = new JTable();
		JTextArea text = new JTextArea();
		/*
		model.getContent();
		model.getStanding();
		model.getStandingLastMatchDay();
		model.getTransferMarket();
		model.getAllPlayers();*/
		
		mainPane.add(text);
		
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Model model = new Model();
		GUI myGui = new GUI(model);
	}
}
