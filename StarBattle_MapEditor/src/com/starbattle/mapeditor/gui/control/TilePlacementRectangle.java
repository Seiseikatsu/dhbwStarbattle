package com.starbattle.mapeditor.gui.control;

import java.awt.Rectangle;

import com.starbattle.mapeditor.layer.MapLayer;


public class TilePlacementRectangle extends TilePlacementMode {

	private int width, height;
	private TileSelection selection;
	
	// Standard tile placement
	public TilePlacementRectangle(MapLayer layer,TileSelection selection, Rectangle rect) {

		setPosition(layer, rect.x, rect.y);
		this.width=rect.width;
		this.height=rect.height;
		this.selection=selection;
	}
	
	public TileSelection getSelection() {
		return selection;
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

}
