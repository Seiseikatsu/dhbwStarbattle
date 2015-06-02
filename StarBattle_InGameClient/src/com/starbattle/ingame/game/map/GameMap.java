package com.starbattle.ingame.game.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.maploader.main.CollisionMap;
import com.starbattle.maploader.main.MapLoadException;
import com.starbattle.maploader.main.MapLoader;

public class GameMap {

	private final static String path = "resource/maps/";
	public final static int TILE_SIZE = 64;
	private TiledMap map;
	private int gameLayerID;
	private CollisionMap collisionMap;
	
	public GameMap() {

	}

	public void loadMap(String name) {
		try {
			MapLoader mapLoader = new MapLoader();
			String file = path + name + ".tmx";

			mapLoader.loadMap(file, false, new MapObjectLoader());
			map = mapLoader.getMap();
			gameLayerID = mapLoader.getGameLayerID();
			int w = map.getWidth();
			int h = map.getHeight();
			collisionMap=mapLoader.getCollisionMap();
			System.out.println("Loaded Map with " + map.getLayerCount() + " Layers [Size:" + w + "x" + h + "]");
		} catch (MapLoadException e) {
			e.printStackTrace();
		}
	}

	public void renderBackground(Viewport viewport) {
		for (int i = 0; i < gameLayerID; i++) {
			map.render(viewport.getMapX(), viewport.getMapY(), i);
		}
	}

	public void renderForeground(Viewport viewport) {
		for (int i = gameLayerID + 1; i < map.getLayerCount(); i++) {

			map.render(viewport.getMapX(), viewport.getMapY(), i);

		}
	}

	public int getGameTileID(int x, int y) {
		return map.getTileId(x, y, gameLayerID);
	}

	public int getWidth() {
		return map.getWidth();
	}

	public int getHeight() {
		return map.getHeight();
	}

	public CollisionMap getCollisionMap() {
		return collisionMap;
	}
}
