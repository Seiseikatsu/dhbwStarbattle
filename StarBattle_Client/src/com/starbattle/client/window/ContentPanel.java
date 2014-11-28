package com.starbattle.client.window;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ContentPanel extends JPanel{

	private Image background=null;
	private CustomPaintInterface customPaintInterface;	
	
	
	public void setBackgroundImage(Image image)
	{
		background=image;
	}
	
	public void setCustomPaintInterface(CustomPaintInterface customPaintInterface) {
		customPaintInterface.setParentView(this);
		this.customPaintInterface = customPaintInterface;
	}
	
	
		
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(background!=null)
		{
		g.drawImage(background,0,0,null);
		}
		if(customPaintInterface!=null)
		{
			Dimension size=new Dimension(this.getWidth(),this.getHeight());
			customPaintInterface.paintPanel(g,size);
		}
	}
	
	
}
