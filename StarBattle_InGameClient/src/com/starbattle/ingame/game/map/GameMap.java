package com.starbattle.ingame.game.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.ingame.game.viewport.Viewport;

public class GameMap {

	private final static String path = "resource/maps/";
	private TiledMap map;
	private int gameLayerID;

	public GameMap() {

	}

	public void loadMap(String name) {
		try {
			//Warning: If resource not found, the client will crash (TiledMap doesnt checks by itself if the path is valid)
			//TODO: check if file exists
			map = new TiledMap(path + name + ".tmx");
			gameLayerID = map.getLayerIndex("Game");
			System.out.println("Loaded Map with " + map.getLayerCount() + " Layers (GameLayerID: " + gameLayerID + ")");
		} catch (SlickException e) {
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

}
