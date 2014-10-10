package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.tiles.TilePlacement;

public abstract class MapLayer  {

	protected String name,resource;
	
	public abstract void clear();
	
	public abstract void setSize(int w, int h);
	
	public abstract void resize(int xplus, int yplus);
	
	public abstract void place(TilePlacement tilePlacement);
	
	public String getName() {
		return name;
	}
	
	public String getResource() {
		return resource;
	}
}
