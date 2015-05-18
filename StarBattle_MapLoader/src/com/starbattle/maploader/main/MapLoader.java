package com.starbattle.maploader.main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapLoader {

	private CollisionMap collisionMap;
	private TiledMap map;
	private int gameLayerID;

	public void loadMap(String path, boolean onlyGameLayer, MapObjectFactory factory) throws MapLoadException {
		try {
			map = new TiledMap(path, !onlyGameLayer);
			gameLayerID = map.getLayerIndex("Game");

			GameTileLoader loader = new GameTileLoader(factory);
			loader.findGameTiles(map, gameLayerID);
			collisionMap = loader.getCollisionMap();

		} catch (SlickException e) {
			e.printStackTrace();
			throw new MapLoadException();
		}
	}

	public CollisionMap getCollisionMap() {
		return collisionMap;
	}

	public int getGameLayerID() {
		return gameLayerID;
	}

	public TiledMap getMap() {
		return map;
	}
}
