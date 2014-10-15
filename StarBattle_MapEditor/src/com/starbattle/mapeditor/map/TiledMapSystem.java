package com.starbattle.mapeditor.map;

import java.awt.Rectangle;

import com.starbattle.mapeditor.gui.control.TilePlacement;
import com.starbattle.mapeditor.map.file.MapFileTiledLayer;
import com.starbattle.mapeditor.resource.AutotileMarks;

public class TiledMapSystem implements MapSystem {

	private Tile[][] map;
	private int width, height;

	public TiledMapSystem() {

	}

	public TiledMapSystem(MapFileTiledLayer layer, AutotileMarks autotile) {
		int width = layer.tiles.length;
		int height = layer.tiles[0].length;
		map = new Tile[width][height];
		this.width = width;
		this.height = height;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int tx = layer.tiles[x][y][0];
				int ty = layer.tiles[x][y][1];
				// convert back to autotile if its in an autotile range
				if (autotile != null) {
					int[] autoid = autotile.getAutotileID(tx, ty);
					if(autoid!=null)
					{
					map[x][y] = new Tile(tx, ty, autoid[0], autoid[1]);
					continue;
					}
				}
				map[x][y] = new Tile(tx, ty);
			}
		}
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
		this.width = width;
		this.height = height;
	}

	@Override
	public void move(int x, int y) {

	}

	@Override
	public void resize(int wplus, int hplus) {

	}

	@Override
	public void placeTile(TilePlacement tilePlacement) {

		Rectangle tile = tilePlacement.getTileSelection();
		int sx = tilePlacement.getXpos();
		int sy = tilePlacement.getYpos();

		if (tilePlacement.isAutotile()) {
			placeAutotile(sx, sy, tile.x, tile.y);
		} else {
			placeStandardSelection(sx, sy, tile);
		}
	}

	private void placeAutotile(int sx, int sy, int tix, int tiy) {

		Tile center = new Tile(tix, tiy, tix, tiy);
		if (sx >= 0 && sy >= 0 && sx < width && sy < height) {

			try {
				Tile tile = AutotilePlacement.getAutotileID(areSameNeighbours(sx, sy, center), center);
				map[sx][sy] = tile;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		updateAutoTiles(sx, sy);

	}

	private void updateAutoTiles(int sx, int sy) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				// dont update new set
				if (x != sx && y != sy) {
					int xp = sx + (x - 1);
					int yp = sy + (y - 1);
					if (xp >= 0 && yp >= 0 && xp < width && yp < height) {

						// check if it is an autotile
						if (map[xp][yp].isAutotile()) {
							Tile center = map[xp][yp];
							// update

							try {
								Tile tile = AutotilePlacement.getAutotileID(areSameNeighbours(xp, yp, center), center);
								map[xp][yp] = tile;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				}
			}
		}
	}

	private boolean[] areSameNeighbours(int xp, int yp, Tile center) {
		boolean[] neighbours = new boolean[9];

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				int tx = (x - 1) + xp;
				int ty = (y - 1) + yp;
				int tid = y * 3 + x;
				if (tx >= 0 && ty >= 0 && tx < width && ty < height) {
					neighbours[tid] = center.isSameAutoTile(map[tx][ty]);
				} else {
					neighbours[tid] = true; // end of map combines
				}
			}
		}
		return neighbours;
	}

	private void placeStandardSelection(int sx, int sy, Rectangle tile) {
		for (int x = 0; x < tile.width; x++) {
			for (int y = 0; y < tile.height; y++) {

				int tix = x + tile.x;
				int tiy = y + tile.y;

				int xp = sx + x;
				int yp = sy + y;
				if (xp >= 0 && yp >= 0 && xp < width && yp < height) {
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
