package com.starbattle.mapeditor.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.starbattle.mapeditor.gui.control.TilePlacementMode;
import com.starbattle.mapeditor.gui.control.TilePlacementMoveBehind;
import com.starbattle.mapeditor.gui.control.TilePlacementMoveSelection;
import com.starbattle.mapeditor.gui.control.TilePlacementMoveSelectionRelease;
import com.starbattle.mapeditor.gui.control.TilePlacementRemove;
import com.starbattle.mapeditor.gui.control.TilePlacementStandard;
import com.starbattle.mapeditor.resource.SpriteSheet;

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

		if (mode instanceof TilePlacementStandard) {
			placeTile((TilePlacementStandard) mode);
		} else if (mode instanceof TilePlacementRemove) {
			removeTile((TilePlacementRemove) mode);
		} else if (mode instanceof TilePlacementMoveBehind) {
			moveBehindTile((TilePlacementMoveBehind) mode);
		} else if (mode instanceof TilePlacementMoveSelection) {
			moveTile((TilePlacementMoveSelection) mode);
		} else if (mode instanceof TilePlacementMoveSelectionRelease) {
			movingTile = null;
		}

	}

	private DecorationTile movingTile;

	private void moveTile(TilePlacementMoveSelection mo) {

		if (movingTile == null) {
			movingTile = getSelectedTile(mo.getXpos(), mo.getYpos());
			;
		}
		if (movingTile != null) {
			int movex = mo.getMoveX();
			int movey = mo.getMoveY();
			movingTile.setPosition(movex + mo.getXpos(), movey + mo.getYpos());
		}
	}

	private void moveBehindTile(TilePlacementMoveBehind mo) {

		DecorationTile tile = getSelectedTile(mo.getXpos(), mo.getYpos());
		if (tile != null) {
			int index = map.indexOf(tile);
			// move one behind
			if (index > 0) {
				index--;
				map.remove(tile);
				map.add(index, tile);
			}
		}
	}

	private void removeTile(TilePlacementRemove mo) {

		DecorationTile tile = getSelectedTile(mo.getXpos(), mo.getYpos());
		if (tile != null) {
			map.remove(tile);
		}
	}

	private void placeTile(TilePlacementStandard tilePlacement) {
		int xpos = tilePlacement.getXpos();
		int ypos = tilePlacement.getYpos();
		Rectangle tile = tilePlacement.getTileSelection();
		map.add(new DecorationTile(xpos, ypos, tile));
	}

	private DecorationTile getSelectedTile(int mx, int my) {
		Point mouse = new Point(mx, my);
		DecorationTile highestTile = null;
		for (int i = 0; i < map.size(); i++) {
			DecorationTile tile = map.get(i);
			int x = tile.getXpos();
			int y = tile.getYpos();
			int w = tile.getTileSelection().width * SpriteSheet.TILE_SIZE;
			int h = tile.getTileSelection().height * SpriteSheet.TILE_SIZE;
			Rectangle size = new Rectangle(x, y, w, h);
			if (size.contains(mouse)) {
				highestTile = tile;
			}
		}
		return highestTile;
	}

	@Override
	public void clear() {
		map.clear();
	}

}
