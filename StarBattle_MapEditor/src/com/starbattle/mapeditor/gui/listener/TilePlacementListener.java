package com.starbattle.mapeditor.gui.listener;

import java.awt.Rectangle;

public interface TilePlacementListener {

	public void placeTile(int mx, int my);
	
	public void removeTile(int mx, int my);
	
	public void fillRectangle(int x, int y, int w, int h);
	
	public void fillTile(int tx, int ty);
	
	public void moveSelectedTile(int mx, int my, int movex, int movey);
}
