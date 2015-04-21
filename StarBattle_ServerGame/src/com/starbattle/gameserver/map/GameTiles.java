package com.starbattle.gameserver.map;

public enum GameTiles {

	COLLISION_TILE(0),
	DEATHBLOCK(6),
	SPAWNPOINT_BLUE(3),
	SPAWMPOINT_RED(2),
	SPAWNPOINT_ALL(1),
	FLAG_BLUE(5),
	FLAG_RED(4);
	
	
	private int tileID;
	
	private GameTiles(int tile) {
		this.tileID=tile;
	}
	
	public int getTileID() {
		return tileID;
	}
	
}
