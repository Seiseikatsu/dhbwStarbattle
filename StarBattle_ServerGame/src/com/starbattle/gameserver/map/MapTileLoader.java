package com.starbattle.gameserver.map;

import com.starbattle.gameserver.game.Team;
import com.starbattle.maploader.main.GameTiles;
import com.starbattle.maploader.main.MapObjectFactory;

public class MapTileLoader implements MapObjectFactory {

	private SpawnPointList spawnPointList;

	public MapTileLoader() {
		spawnPointList = new SpawnPointList();
	}

	@Override
	public void createObject(int x, int y, GameTiles tile) {
		switch (tile) {
		case SPAWMPOINT_RED:
			spawnPointList.add(new SpawnPoint(x, y, Team.RED_TEAM));
			break;
		case SPAWNPOINT_BLUE:
			spawnPointList.add(new SpawnPoint(x, y, Team.BLUE_TEAM));
			break;
		case SPAWNPOINT_ALL:
			spawnPointList.add(new SpawnPoint(x, y, Team.NO_TEAM));
			break;
		}
	}

	public SpawnPointList getSpawnPointList() {
		return spawnPointList;
	}

}
