package com.starbattle.gameserver.map;

import java.util.ArrayList;

import com.starbattle.gameserver.game.Team;

public class SpawnPointList {

	private ArrayList<SpawnPoint> spawnPointsBlue=new ArrayList<SpawnPoint>();
	private ArrayList<SpawnPoint> spawnPointsRed=new ArrayList<SpawnPoint>();

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

		Team team=spawnPoint.getTeam() ;
		if (team== Team.BLUE_TEAM) {
			spawnPointsBlue.add(spawnPoint);
		} else if(team ==Team.RED_TEAM){
			spawnPointsRed.add(spawnPoint);
		} else {
			//for both teams ( /no teams )
			spawnPointsBlue.add(spawnPoint);
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
