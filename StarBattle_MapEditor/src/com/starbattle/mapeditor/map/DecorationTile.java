package com.starbattle.mapeditor.map;

import java.awt.Rectangle;

import com.starbattle.mapeditor.resource.SpriteSheet;

public class DecorationTile {

	private int xpos,ypos;
	private Rectangle tileSelection;
	
	public DecorationTile(int xpos, int ypos, Rectangle tileSelection)
	{
		this.xpos=xpos;
		this.ypos=ypos;
		this.tileSelection=tileSelection;
	}
	
	public void setPosition(int x, int y)
	{
		xpos=x-(tileSelection.width*SpriteSheet.TILE_SIZE)/2;
		ypos=y-(tileSelection.width*SpriteSheet.TILE_SIZE)/2;
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
