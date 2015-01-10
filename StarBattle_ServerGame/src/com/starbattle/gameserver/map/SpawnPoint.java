package com.starbattle.gameserver.map;

import com.starbattle.gameserver.game.Team;

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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
