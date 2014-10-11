package com.starbattle.mapeditor.map;

import java.awt.Rectangle;

import com.starbattle.mapeditor.gui.control.TilePlacement;

public class TiledMapSystem implements MapSystem {

	private Tile[][] map;

	public TiledMapSystem() {

	}

	public Tile[][] getMap() {
		return map;
	}

	public Tile getTile(int x, int y) {
		return map[x][y];
	}

	@Override
	public void init(int width, int height) {

		map = new Tile[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map[x][y] = new Tile();
			}
		}
	}

	@Override
	public void move(int x, int y) {

	}

	@Override
	public void resize(int wplus, int hplus) {

	}

	@Override
	public void placeTile(TilePlacement tilePlacement) {

		int sx = tilePlacement.getXpos();
		int sy = tilePlacement.getYpos();
		Rectangle tile = tilePlacement.getTileSelection();

		int w=map.length;
		int h=map[0].length;
		
		for (int x = 0; x < tile.width; x++) {
			for (int y = 0; y < tile.height; y++) {

				int tix = x + tile.x;
				int tiy = y + tile.y;

				int xp = sx + x;
				int yp = sy + y;
				if(xp>=0&&yp>=0&&xp<w&&yp<h)
				{
				map[xp][yp] = new Tile(tix, tiy);
				}
			}
		}

	}

	@Override
	public void clear() {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				map[x][y] = new Tile();
			}
		}
	}

}
