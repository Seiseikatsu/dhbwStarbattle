package com.starbattle.gameserver.map;

import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.map.collision.CollisionMap;

public class GameTileLoader {


	private static SpawnPointList spawnPointList;
	private static CollisionMap collisionMap;

	private static GameTiles getSpecialTile(int id) {
		for (GameTiles tile : GameTiles.values()) {
			if (tile.getTileID() == id) {
				return tile;
			}
		}
		return null;
	}

	public static void findGameTiles(TiledMap map, int gameLayerID) {

		spawnPointList = new SpawnPointList();
		
		int w=map.getWidth();
		int h=map.getHeight();
		collisionMap=new CollisionMap(w, h);
		
		// search
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int tileID = map.getTileId(x, y, gameLayerID)-1;

				GameTiles specialTile = getSpecialTile(tileID);
				if (specialTile != null) {
					foundGameTile(specialTile, x, y);
				}
			}
		}
	}

	private static void foundGameTile(GameTiles tile, int x, int y) {
		switch (tile) {
		case SPAWNPOINT_ALL:
			spawnPointList.add(new SpawnPoint(x, y, Team.NO_TEAM));	
			break;
		case SPAWNPOINT_BLUE:
			spawnPointList.add(new SpawnPoint(x, y, Team.BLUE_TEAM));
			break;
		case SPAWMPOINT_RED:
			spawnPointList.add(new SpawnPoint(x, y, Team.RED_TEAM));
			break;
		}
	}

	public static SpawnPointList getSpawnPointList() {
		return spawnPointList;
	}

	public static CollisionMap getCollisionMap() {
		return collisionMap;
	}
}
