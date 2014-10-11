package com.starbattle.mapeditor.map;

import java.awt.Rectangle;

public class DecorationTile {

	private int xpos,ypos;
	private Rectangle tileSelection;
	
	public DecorationTile(int xpos, int ypos, Rectangle tileSelection)
	{
		this.xpos=xpos;
		this.ypos=ypos;
		this.tileSelection=tileSelection;
	}
	
	public void move(int x, int y)
	{
		xpos+=x;
		ypos+=y;
	}
	
	public Rectangle getTileSelection() {
		return tileSelection;
	}
	
	public int getXpos() {
		return xpos;
	}
	
	public int getYpos() {
		return ypos;
	}
	
}
