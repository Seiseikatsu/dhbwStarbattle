package com.starbattle.client.layout;


import javax.swing.JPanel;

public abstract class BackgroundViewModel {

	protected BackgroundPanel view=new BackgroundPanel();
	
	public JPanel getView() {
		return view;
	}
	
}
