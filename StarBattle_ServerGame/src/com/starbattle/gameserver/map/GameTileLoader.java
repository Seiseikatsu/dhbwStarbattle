package com.starbattle.gameserver.map;

import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.game.Team;

public class GameTileLoader {


	private static SpawnPointList spawnPointList;

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
		// search
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
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

}
