package com.starbattle.gameserver.game;

public enum Team {

	BLUE_TEAM(0),RED_TEAM(1);
	
	private int id;
	
	Team(int id)
	{
		this.id=id;
	}
	
	public int getTeamId() {
		return id;
	}
	
	public boolean isSameTeam(Team team)
	{
		return id==team.getTeamId();
	}
	
	public boolean isEnemyTeam(Team team)
	{
		return id!=team.getTeamId();
	}
}
