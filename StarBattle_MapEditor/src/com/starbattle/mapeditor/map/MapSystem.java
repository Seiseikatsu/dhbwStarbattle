package com.starbattle.mapeditor.map;

import com.starbattle.mapeditor.gui.control.TilePlacementMode;

public interface MapSystem {

	public void init(int width, int height);
	
	public void move(int x, int y);
	
	public void resize(int wplus, int hplus);
	
	public void placeTile(TilePlacementMode tilePlacement);
	
	public void clear();


	
}
