package com.starbattle.gameserver.map;

import java.util.ArrayList;

import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.game.Team;

public class GameTileLoader {

	// IDs in Game TileSet (resource/tilesets/gameset.png) for Blocks
	public final static int TILE_COLLISION = 1;
	public final static int TILE_DEATHBLOCK = 2;
	public final static int TILE_SPAWNPOINT_BLUE = 3;
	public final static int TILE_SPAWNPOINT_RED = 4;
	public final static int TILE_FLAG_BLUE = 5;
	public final static int TILE_FLAG_RED = 6;

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
				int tileID = map.getTileId(x, y, gameLayerID);

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
