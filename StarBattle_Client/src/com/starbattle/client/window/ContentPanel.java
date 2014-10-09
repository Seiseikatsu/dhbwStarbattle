package com.starbattle.client.window;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ContentPanel extends JPanel{

	private Image background=null;
	
	
	
	public void setBackgroundImage(Image image)
	{
		background=image;
	}
	

	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(background!=null)
		{
		g.drawImage(background,0,0,null);
		}
	}
	
}
