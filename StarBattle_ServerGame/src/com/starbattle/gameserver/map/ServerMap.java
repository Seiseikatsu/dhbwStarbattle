package com.starbattle.gameserver.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.exceptions.ServerMapException;
import com.starbattle.maploader.main.CollisionMap;
import com.starbattle.maploader.main.MapLoadException;
import com.starbattle.maploader.main.MapLoader;

public class ServerMap {

	public final static int TILE_SIZE = 64;
	public final static String path = "resource/maps/";
	private TiledMap map;
	private int gameLayerID;
	private SpawnPointList spawnPoints;
	private CollisionMap collisionMap;
	private MapBorder mapBorder;

	public ServerMap(String mapName) throws ServerMapException {
		try {
			String file = path + mapName + ".tmx";

			MapTileLoader tileLoader = new MapTileLoader();
			MapLoader mapLoader = new MapLoader();
			
			mapLoader.loadMap(file, true, tileLoader);
			map=mapLoader.getMap();
			spawnPoints = tileLoader.getSpawnPointList();
			collisionMap = mapLoader.getCollisionMap();
			mapBorder = new MapBorder(map.getWidth(), map.getHeight());
			if (!spawnPoints.isValidList()) {
				throw new ServerMapException("No Spawnpoints found for both teams!");
			}
		} catch (MapLoadException e) {
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
