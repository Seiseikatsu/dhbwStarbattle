package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.gui.control.TilePlacementMode;
import com.starbattle.mapeditor.map.MapSystem;

public abstract class MapLayer {

	protected String name, resource;
	protected boolean isVisible = true;
	protected MapSystem map;
	
	public void clear() {
		map.clear();
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}

	public void move(int xplus, int yplus) {
		map.move(xplus, yplus);
	}

	public void init(int w, int h) {
		map.init(w, h);
	}

	public void resize(int xplus, int yplus) {
		map.resize(xplus, yplus);
	}

	public void place(TilePlacementMode tilePlacement) {
		map.placeTile(tilePlacement);
	}

	public String getName() {
		return name;
	}

	public String getResource() {
		return resource;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void toggleVisibility() {
		isVisible = !isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public MapSystem getMap() {
		return map;
	}

}
