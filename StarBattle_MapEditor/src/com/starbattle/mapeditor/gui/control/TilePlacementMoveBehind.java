package com.starbattle.mapeditor.gui.control;

import com.starbattle.mapeditor.layer.MapLayer;

public class TilePlacementMoveBehind extends TilePlacementMode {

	

	// Standard tile placement
	public TilePlacementMoveBehind(MapLayer layer, int xpos, int ypos) {

		setPosition(layer, xpos, ypos);

	}


}
