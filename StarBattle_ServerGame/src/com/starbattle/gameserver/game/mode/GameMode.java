package com.starbattle.gameserver.game.mode;

import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.map.SpawnPoint;
import com.starbattle.gameserver.map.SpawnPointList;
import com.starbattle.gameserver.player.GamePlayer;

public abstract class GameMode implements GameModeInterface {

	protected GameContainer game;
	protected GamePoints points;
	protected SpawnPointList spawnPointList;
	
	public GameMode(GameContainer game)
	{
		this.game=game;
		this.spawnPointList=game.getServerMap().getSpawnPoints();
		this.points=new GamePoints(game.getPlayerList());
	}
	

	protected void endGame(Team winnerTeam)
	{
		
	}
	
	protected void endGame(GamePlayer winnerPlayer)
	{
		
	}
	
	protected void endGame()
	{
		//just end, no winner
	}
	
	protected void defaulTeamEndCheck(int pointLimit)
	{
		Team team=points.getTeamWithMostPoints();
		if(points.getTeamPoints(team)>=pointLimit)
		{
			endGame(team);
		}
	}
	
	protected void defaulPlayerEndCheck(int pointLimit)
	{
		GamePlayer player=points.getPlayerWithMostPoints();
		if(player.getPoints()>=pointLimit)
		{
			endGame(player);
		}
	}
	
	
	protected void defaultRespawn(GamePlayer player, int respawnTime)
	{
		SpawnPoint spawnpoint=spawnPointList.getRandomSpawnPoint(player.getTeam());
		player.startRespawntimer(spawnpoint, respawnTime);
	}
	
	protected void defaultDamageProcess(GamePlayer player, Damage damage, int pointLose, int killerPointAdd)
	{
		player.takeDamge(damage);
		if(player.getHealth().isDead())
		{
			//player lose points
			points.addPlayerPoints(player, -pointLose);
			
			//player add points
			points.addPlayerPoints(damage.getDamageDealer(), killerPointAdd);
			
			//start respawn
			int time=getRespawnTime(player);
			defaultRespawn(player, time);
		}
	}
	
	protected abstract int getRespawnTime(GamePlayer player);
	
}
