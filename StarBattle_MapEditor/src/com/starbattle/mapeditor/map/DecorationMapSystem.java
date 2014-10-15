package com.starbattle.mapeditor.map;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.starbattle.mapeditor.gui.control.TilePlacementMode;
import com.starbattle.mapeditor.gui.control.TilePlacementStandard;

public class DecorationMapSystem implements MapSystem {

	private ArrayList<DecorationTile> map;

	public DecorationMapSystem() {

	}

	public DecorationMapSystem(ArrayList<DecorationTile> tiles) {
		map = tiles;
	}

	public ArrayList<DecorationTile> getMap() {
		return map;
	}

	@Override
	public void init(int width, int height) {
		map = new ArrayList<DecorationTile>();
	}

	@Override
	public void move(int x, int y) {
		// move all tiles
		for (DecorationTile tile : map) {
			tile.move(x, y);
		}
	}

	@Override
	public void resize(int wplus, int hplus) {
		// do nothing
	}

	@Override
	public void placeTile(TilePlacementMode mode) {

		if(mode instanceof TilePlacementStandard)
		{
		TilePlacementStandard tilePlacement=(TilePlacementStandard)mode;
		int xpos = tilePlacement.getXpos();
		int ypos = tilePlacement.getYpos();
		Rectangle tile = tilePlacement.getTileSelection();
		map.add(new DecorationTile(xpos, ypos, tile));
		}
	}

	@Override
	public void clear() {
		map.clear();
	}

}
