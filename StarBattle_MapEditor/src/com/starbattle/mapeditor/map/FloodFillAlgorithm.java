package com.starbattle.mapeditor.map;

import java.awt.Rectangle;
import java.util.Stack;

public class FloodFillAlgorithm {

	public static void floodFill(TiledMapSystem map, int sx, int sy, Tile tile) {
		Stack<Pixel> stack = new Stack<Pixel>();
		stack.push(new Pixel(sx, sy));
		Tile otile = map.getTile(sx, sy);
		Tile oldtile = null;
		if (otile.isAutotile()) {
			oldtile = new Tile(otile.getX(), otile.getY(), otile.getAutox(), otile.getAutoy());
			if(oldtile.isSameAutoTile(tile))
			{
				return;
			}
		} else {
			oldtile = new Tile(otile.getX(), otile.getY());
			if(oldtile.isSameTile(tile))
			{
				return;
			}
		}
		while (!stack.isEmpty()) {

			Pixel p = stack.pop();
			int x = p.x;
			int y = p.y;
			if (map.isTileInbounds(x, y)) {

				Tile t = map.getTile(x, y);
				boolean place = false;
				if (oldtile.isAutotile()) {
					place = t.isSameAutoTile(oldtile);
				} else {
					place= t.isSameTile(oldtile);
				}
				
				if (place) {
					placetile(map, tile, x, y);
					stack.push(new Pixel(x, y + 1));
					stack.push(new Pixel(x, y - 1));
					stack.push(new Pixel(x + 1, y));
					stack.push(new Pixel(x - 1, y));
				}
			}
		}

	}

	private static void placetile(TiledMapSystem map, Tile tile, int x, int y) {
		if (tile.isAutotile()) {
			map.placeAutotile(x, y, tile.getAutox(), tile.getAutoy());
		} else {
			Rectangle newtile = new Rectangle(tile.getX(), tile.getY(), 1, 1);
			map.placeStandardSelection(x, y, newtile);
		}
	}

	private static class Pixel {
		public int x, y;

		public Pixel(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
