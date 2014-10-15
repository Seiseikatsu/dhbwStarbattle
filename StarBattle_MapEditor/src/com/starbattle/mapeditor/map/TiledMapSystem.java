package com.starbattle.mapeditor.map;

import java.awt.Rectangle;

import com.starbattle.mapeditor.gui.control.TilePlacementFill;
import com.starbattle.mapeditor.gui.control.TilePlacementMode;
import com.starbattle.mapeditor.gui.control.TilePlacementRectangle;
import com.starbattle.mapeditor.gui.control.TilePlacementRemove;
import com.starbattle.mapeditor.gui.control.TilePlacementStandard;
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
					if (autoid != null) {
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
	public void placeTile(TilePlacementMode mode) {

		if (mode instanceof TilePlacementStandard) {
			placeTile((TilePlacementStandard) mode);
		} else if (mode instanceof TilePlacementRemove) {
			removeTile((TilePlacementRemove) mode);
		} else if (mode instanceof TilePlacementRectangle) {
			fillRectangle((TilePlacementRectangle) mode);
		} else if (mode instanceof TilePlacementFill) {
			fillFlood((TilePlacementFill) mode);
		}
	}

	private void fillFlood(TilePlacementFill tilePlacement) {
		
		int tx=tilePlacement.getSelection().getX();
		int ty=tilePlacement.getSelection().getY();
		if(isTileInbounds(tx, ty))
		{
		Tile tile=null;
		if(tilePlacement.getSelection().isAutotile())
		{
			tile=new Tile(tx,ty,tx,ty);
		}
		else
		{
			tile=new Tile(tx,ty);
		}
		int x=tilePlacement.getXpos();
		int y=tilePlacement.getYpos();
		FloodFillAlgorithm.floodFill(this, x, y, tile);
		}
	}

	private void fillRectangle(TilePlacementRectangle tilePlacement) {

		Rectangle tile = tilePlacement.getSelection().getSelectionAsRectangle();
		for (int x = 0; x < tilePlacement.getWidth(); x++) {
			for (int y = 0; y < tilePlacement.getHeight(); y++) {
				int xp = x + tilePlacement.getXpos();
				int yp = y + tilePlacement.getYpos();
				if (tilePlacement.getSelection().isAutotile()) {
					placeAutotile(xp, yp, tile.x, tile.y);
				} else {
					placeStandardSelection(xp, yp, tile);
				}
			}
		}
	}

	private void removeTile(TilePlacementRemove tilePlacement) {
		int sx = tilePlacement.getXpos();
		int sy = tilePlacement.getYpos();
		Tile oldTile=map[sx][sy];
		if(isTileInbounds(sx, sy))
		{
		map[sx][sy] = new Tile();
		
		if(oldTile.isAutotile())
		{
			//update autotiles
			updateAutoTiles(sx, sy);
		}
		}
	}
	
	

	private void placeTile(TilePlacementStandard tilePlacement) {
		Rectangle tile = tilePlacement.getTileSelection();
		int sx = tilePlacement.getXpos();
		int sy = tilePlacement.getYpos();

		if (tilePlacement.isAutotile()) {
			placeAutotile(sx, sy, tile.x, tile.y);
		} else {
			placeStandardSelection(sx, sy, tile);
		}
	}

	public void placeAutotile(int sx, int sy, int tix, int tiy) {

		Tile center = new Tile(tix, tiy, tix, tiy);
		if(isTileInbounds(sx, sy))
		{
			try {
				Tile tile = AutotilePlacement.getAutotile(areSameNeighbours(sx, sy, center), center);
				map[sx][sy] = tile;
			} catch (Exception e) {
			
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
					if(isTileInbounds(xp, yp))
					{
						// check if it is an autotile
						if (map[xp][yp].isAutotile()) {
							Tile center = map[xp][yp];
							// update
							try {
								Tile tile = AutotilePlacement.getAutotile(areSameNeighbours(xp, yp, center), center);
								map[xp][yp] = tile;
							} catch (Exception e) {
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
				if(isTileInbounds(tx, ty))
				{
					neighbours[tid] = center.isSameAutoTile(map[tx][ty]);
				} else {
					neighbours[tid] = true; // end of map combines
				}
			}
		}
		return neighbours;
	}

	public void placeStandardSelection(int sx, int sy, Rectangle tile) {
		for (int x = 0; x < tile.width; x++) {
			for (int y = 0; y < tile.height; y++) {

				int tix = x + tile.x;
				int tiy = y + tile.y;

				int xp = sx + x;
				int yp = sy + y;
				if(isTileInbounds(xp, yp))
				{
					map[xp][yp] = new Tile(tix, tiy);
				}
			}
		}
	}
	
	public boolean isTileInbounds(int x, int y)
	{
		if(x>=0&&y>=0&&x<width&&y<height)
		{
			return true;
		}
		return false;
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
