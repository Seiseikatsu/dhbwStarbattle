package com.starbattle.mapeditor.gui.control;

import java.awt.Rectangle;

import com.starbattle.mapeditor.layer.MapLayer;

public class TilePlacementStandard extends TilePlacementMode {

	private Rectangle tileSelection;
	private boolean isAutotile;

	// Standard tile placement
	public TilePlacementStandard(MapLayer layer, TileSelection selection, int xpos, int ypos) {

		setPosition(layer, xpos, ypos);

		// set tileset selection area
		int x = selection.getX();
		int y = selection.getY();
		int w = selection.getW();
		int h = selection.getH();

		tileSelection = new Rectangle(x, y, w, h);
		isAutotile = selection.isAutotile();
	
	}

	public boolean isAutotile() {
		return isAutotile;
	}

	public Rectangle getTileSelection() {
		return tileSelection;
	}

}
