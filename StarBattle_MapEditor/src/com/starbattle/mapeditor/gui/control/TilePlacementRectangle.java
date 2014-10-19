package com.starbattle.mapeditor.gui.control;

import java.awt.Rectangle;

import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.resource.SpriteSheet;


public class TilePlacementRectangle extends TilePlacementMode {

	private int width, height;
	private TileSelection selection;
	
	// Standard tile placement
	public TilePlacementRectangle(MapLayer layer,TileSelection selection, Rectangle rect) {

		setPosition(layer, rect.x, rect.y);
		this.width=rect.width/SpriteSheet.TILE_SIZE+1;
		this.height=rect.height/SpriteSheet.TILE_SIZE+1;
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
