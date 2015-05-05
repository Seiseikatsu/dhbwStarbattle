package com.starbattle.gameserver.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.exceptions.ServerMapException;

public class ServerMap {

	public final static String path = "resource/maps/";
	private TiledMap map;
	private int gameLayerID;
	private SpawnPointList spawnPoints;

	public static int TILE_SIZE=64;
	
	public ServerMap(String mapName) throws ServerMapException {
		try {
		
			map = new TiledMap(path + mapName + ".tmx",false);
			gameLayerID = map.getLayerIndex("Game");

			//find game tiles
			GameTileLoader.findGameTiles(map, gameLayerID);
			// load spawnpoints from map tiles
			spawnPoints = GameTileLoader.getSpawnPointList();

			if (!spawnPoints.isValidList()) {
				throw new ServerMapException("No Spawnpoints found for both teams!");
			}
		} catch (SlickException e) {
			e.printStackTrace();
			throw new ServerMapException("Error loading Map!");
		}

	}

	public int getBlockID(int x, int y) {
		return map.getTileId(x, y, gameLayerID);
	}

	public SpawnPointList getSpawnPoints() {
		return spawnPoints;
	}

	public TiledMap getMap() {
		return map;
	}

}
