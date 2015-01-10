package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.map.SpawnPoint;

public class GamePlayer {

	private Team team;
	private Health health;
	private float x,y;
	private int points;
	private String playerName;
	private int playerID;
	private boolean carriesFlag;
	private RespawnTimer respawnTimer;
	private PlayerRespawnListener respawnListener;
	
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
		if(!health.isDead())
		{
		health.takeDamage(damage);
		}
	}
	
	public void startRespawntimer(final SpawnPoint spawnpoint, int time)	
	{
		respawnTimer.startRespawnTimer(time, new RespawnListener() {		
			@Override
			public void doRespawn() {
				respawnPlayer(spawnpoint.getX(), spawnpoint.getY());
			}
		});
	}
	
	private void respawnPlayer(float x, float y)
	{
		respawnListener.playerRespawned(this);
		this.x=x;
		this.y=y;
		health.revive();
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

	public void setRespawnListener(PlayerRespawnListener respawnListener) {
		this.respawnListener=respawnListener;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
}
