package com.starbattle.gameserver.map;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.physics.Location;

public class SpawnPoint {

	
	private int x,y;
	private Team team;
	
	public SpawnPoint(int x, int y, Team team)
	{
		this.x=x;
		this.y=y;
		this.team=team;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public Location getLocation()
	{
		float xp=x*ServerMap.TILE_SIZE;
		float yp=y*ServerMap.TILE_SIZE;
		xp+=ServerMap.TILE_SIZE/2;
		yp+=ServerMap.TILE_SIZE/2;
		return new Location(xp,yp);
	}
	
}
