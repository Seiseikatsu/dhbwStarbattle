package com.starbattle.mapeditor.gui.control;

import java.awt.Rectangle;

import com.starbattle.mapeditor.layer.MapLayer;

public class TilePlacementMoveSelection extends TilePlacementMode {

	private int movex;
	private int movey;
	
	// Standard tile placement
	public TilePlacementMoveSelection(MapLayer layer, int xpos, int ypos, int movex, int movey) {

		setPosition(layer, xpos, ypos);
		this.movex=movex;
		this.movey=movey;
	
	}

	public int getMoveX() {
		return movex;
	}
	
	public int getMoveY() {
		return movey;
	}


}
