package com.starbattle.gameserver.map;

import java.util.ArrayList;

import com.starbattle.gameserver.game.Team;

public class SpawnPointList {

	private ArrayList<SpawnPoint> spawnPointsBlue;
	private ArrayList<SpawnPoint> spawnPointsRed;

	public SpawnPointList() {

	}

	public SpawnPoint getRandomSpawnPoint(Team team) {

		if (team == Team.BLUE_TEAM) {
			int r = (int) (Math.random() * spawnPointsBlue.size());
			return spawnPointsBlue.get(r);
		} else {
			int r = (int) (Math.random() * spawnPointsRed.size());
			return spawnPointsRed.get(r);
		}
	}

	public void add(SpawnPoint spawnPoint) {

		if (spawnPoint.getTeam() == Team.BLUE_TEAM) {
			spawnPointsBlue.add(spawnPoint);
		} else {
			spawnPointsRed.add(spawnPoint);
		}
	}

	public int getBlueSpawnpointsCount() {
		return spawnPointsBlue.size();
	}

	public int getRedSpawnpointsCount() {
		return spawnPointsRed.size();
	}

	public boolean isValidList() {
		return getBlueSpawnpointsCount() > 0 && getRedSpawnpointsCount() > 0;
	}

}
