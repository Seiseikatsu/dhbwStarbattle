package com.starbattle.gameserver.game.mode;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.PlayerList;

public class GamePoints {

	private PlayerList players;
	
	public GamePoints(PlayerList players)
	{
		this.players=players;		
	}
	
	public boolean playerEnoughPoints(GamePlayer player, int points)
	{
		return player.getPoints()>=points;
	}
	
	public boolean teamEnoughPoints(Team team, int points)
	{
		return getTeamPoints(team)>=points;
	}
	
	public void addPlayerPoints(GamePlayer player, int points)
	{
		int p=player.getPoints();
		p+=points;		
		player.setPoints(p);
	}
	
	public void addTeamPoints(Team team, int points)
	{
		for(GamePlayer player: players.getPlayers())
		{
			if(player.getTeam()==team)
			{
				addPlayerPoints(player, points);
			}
		}	
	}
	
	public int getTeamPoints(Team team)
	{
		int p=0;
		for(GamePlayer player: players.getPlayers())
		{
			if(player.getTeam()==team)
			{
				p+=player.getPoints();
			}
		}
		return p;
	}
	
	public Team getTeamWithMostPoints()
	{
		Team t=null;
		int p=Integer.MIN_VALUE;
		for(int i=0; i<Team.values().length; i++)
		{
			Team team=Team.values()[i];
			int points=getTeamPoints(team);
			if(points>p)
			{
				p=points;
				t=team;
			}
		}
		return t;
	}
	
	public GamePlayer getPlayerWithMostPoints()
	{
		GamePlayer pl=null;
		int p=0;
		for(int i=0; i<players.getPlayers().size(); i++)
		{
			GamePlayer player=players.getPlayer(i);
			int points=player.getPoints();
			if(points>p)
			{
				p=points;
				pl=player;
			}
		}
		return pl;
	}
	
}
