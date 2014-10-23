package com.starbattle.client.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.border.AbstractBorder;

import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.resource.ResourceLoader;

public class DesignWindowBorder extends AbstractBorder{


	private Insets border;
	private Font titleFont=GUIDesign.labelFont.deriveFont(Font.BOLD);
	private Image close=ResourceLoader.loadImage("cross.png");
	private Image icon=ResourceLoader.loadImage("windowIcon.png",22,22);
	private int crossx,crosswidth=26;
	private String title;
	
	public DesignWindowBorder(String title)
	{
		border=new  Insets(25, 4, 4, 4);
		this.title=title;
	}
	
	
	public boolean isMouseOnCloseButton(Point mouse)
	{
		int x=mouse.x;
		int y=mouse.y;
		return (x>=crossx&&x<=crossx+crosswidth&&y<border.top);
	}
	
	public boolean isMouseOnDragArea(Point mouse)
	{
		return mouse.y<border.top;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		

	
		g.setColor(new Color(100,100,100));	
		g.fillRect(0,0,border.left,height);
		g.fillRect(width-border.right,0,border.right,height);
		g.fillRect(0,height-border.bottom,width,border.bottom);
		
		g.setColor(new Color(130,130,130));		
		g.fillRect(0,0,width,border.top);
		
		g.setColor(new Color(70,70,70));
		g.setFont(titleFont);
		g.drawString(title,30,20);
		g.drawImage(icon,4,2,null);
	
		crossx=width-crosswidth-border.right;
		g.fillRect(crossx,0,crosswidth,border.top);
		g.drawImage(close,crossx+5,5,null);
		
		g.setColor(Color.BLACK);
		g.drawRect(0,0,width-1,height-1);
		
		g.setColor(new Color(150,150,150));
		g.drawRect(border.left-1,border.top-1,width-border.right-border.left+1,height-border.bottom-border.top+1);
		
		
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
		return border;
	}
	

}
