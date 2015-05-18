package com.starbattle.maploader.main;

import org.newdawn.slick.tiled.TiledMap;

public class GameTileLoader {

	private CollisionMap collisionMap;
	private MapObjectFactory mapObjectFactory;

	public GameTileLoader(MapObjectFactory mapObjectFactory) {
		this.mapObjectFactory = mapObjectFactory;

	}

	private GameTiles getSpecialTile(int id) {
		for (GameTiles tile : GameTiles.values()) {
			if (tile.getTileID() == id) {
				return tile;
			}
		}
		return null;
	}

	public void findGameTiles(TiledMap map, int gameLayerID) {

		int w = map.getWidth();
		int h = map.getHeight();
		collisionMap = new CollisionMap(w, h);

		// search
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int tileID = map.getTileId(x, y, gameLayerID) - 1;

				GameTiles specialTile = getSpecialTile(tileID);
				if (specialTile != null) {
					foundGameTile(specialTile, x, y);
				}
			}
		}
	}

	private void foundGameTile(GameTiles tile, int x, int y) {
		if (tile == GameTiles.COLLISION_TILE) {
			collisionMap.blockTile(x, y);
		}
		mapObjectFactory.createObject(x, y, tile);
	}

	public CollisionMap getCollisionMap() {
		return collisionMap;
	}
}
