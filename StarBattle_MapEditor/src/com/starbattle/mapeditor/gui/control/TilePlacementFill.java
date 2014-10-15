package com.starbattle.mapeditor.gui.control;

import com.starbattle.mapeditor.layer.MapLayer;

public class TilePlacementFill extends TilePlacementMode {

	private TileSelection selection;

	// Standard tile placement
	public TilePlacementFill(MapLayer layer,TileSelection selection, int xpos, int ypos) {

		setPosition(layer, xpos, ypos);
		this.selection=selection;
	}

	public TileSelection getSelection() {
		return selection;
	}

}
