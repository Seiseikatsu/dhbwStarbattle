package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;

public class GamePlayer {

	private Team team;
	private Health health;
	private float x,y;
	private String playerName;
	private int playerID;
	private boolean carriesFlag;
	
	public GamePlayer(String playerName, int playerID)
	{
		this.playerID=playerID;
		this.playerName=playerName;
	}
	
	public void update(float delta)
	{
		
	}
	
	public void takeDamge(Damage damage)
	{
		health.takeDamage(damage);
	}
	
	
	public Health getHealth() {
		return health;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public boolean isCarriesFlag() {
		return carriesFlag;
	}
}
