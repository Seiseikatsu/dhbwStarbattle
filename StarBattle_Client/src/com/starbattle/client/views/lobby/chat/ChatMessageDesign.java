package com.starbattle.client.views.lobby.chat;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.layout.DesignLabel;

public class ChatMessageDesign extends JPanel{

	
	public ChatMessageDesign( String message, boolean right)
	{
		Color c=null;
		if(right)
		{
			setLayout(new FlowLayout(FlowLayout.RIGHT));			
			c=new Color(0,0,200);
		}
		else
		{
			setLayout(new FlowLayout(FlowLayout.LEFT));
			c=new Color(50,50,50);
		}
		initLayout(message,c);
	}
	
	private void initLayout(String message, Color c)
	{
		setOpaque(false);
		JLabel text=new DesignLabel(message, c, 14);
		add(text);
	}
}
