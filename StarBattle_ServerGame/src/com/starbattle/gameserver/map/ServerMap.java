package com.starbattle.gameserver.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.exceptions.ServerMapException;
import com.starbattle.gameserver.map.collision.CollisionMap;

public class ServerMap {

	public final static int TILE_SIZE=64;
	public final static String path = "resource/maps/";
	private TiledMap map;
	private int gameLayerID;
	private SpawnPointList spawnPoints;
	private CollisionMap collisionMap;
	private MapBorder mapBorder;

	public ServerMap(String mapName) throws ServerMapException {
		try {
			map = new TiledMap(path + mapName + ".tmx",false);
			gameLayerID = map.getLayerIndex("Game");

			//find game tiles
			GameTileLoader.findGameTiles(map, gameLayerID);
			// load spawnpoints from map tiles
			spawnPoints = GameTileLoader.getSpawnPointList();
			collisionMap= GameTileLoader.getCollisionMap();
			mapBorder=new MapBorder(map.getWidth(), map.getHeight());
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

	public CollisionMap getCollisionMap() {
		return collisionMap;
	}
	
	public MapBorder getMapBorder() {
		return mapBorder;
	}
}
