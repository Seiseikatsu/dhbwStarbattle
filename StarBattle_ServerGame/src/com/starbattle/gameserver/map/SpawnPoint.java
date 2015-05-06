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
		float xp=x+0.5f;
		float yp=y+0.5f;
		return new Location(xp,yp);
	}
	
}
