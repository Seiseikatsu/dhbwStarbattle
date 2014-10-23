package com.starbattle.client.window;

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
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(background!=null)
		{
		g.drawImage(background,0,0,null);
		}
		if(customPaintInterface!=null)
		{
			customPaintInterface.paintPanel(g);
		}
	}
	
	
}
