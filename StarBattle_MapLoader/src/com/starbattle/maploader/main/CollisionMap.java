package com.starbattle.maploader.main;


public class CollisionMap {

	private boolean[][] blocked;

	public CollisionMap(int width, int height) {

		blocked = new boolean[width][height];

	}

	public void blockTile(int x, int y) {
		blocked[x][y] = true;
	}

	public void unblockTile(int x, int y) {
		blocked[x][y] = false;
	}

	public boolean isTileBlocked(int x, int y) {
		if (x > -1 && y > -1) {
			if (x < blocked.length && y < blocked[0].length) {
				return blocked[x][y];
			}
		}
		return false;
	}

}
