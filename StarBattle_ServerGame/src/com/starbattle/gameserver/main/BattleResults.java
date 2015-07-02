package com.starbattle.gameserver.main;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;

public class BattleResults {

	private int winnerID=-1;
	private boolean teamGame;
	private List<PlayerResults> results;
	
	
	public BattleResults(PlayerList players)
	{
		results=new ArrayList<PlayerResults>();
		for(GamePlayer p: players.getPlayers())
		{
			PlayerResults r=new PlayerResults(p);
			results.add(r);
		}
	}
	
	
	public void teamWon(Team team)
	{		
		teamGame=true;
		winnerID=team.getTeamId();
	}
	
	
	public void playerWon(GamePlayer player)
	{	
		teamGame=false;
		winnerID=player.getAttributes().getPlayerID();
	}
	
	public boolean isTeamGame() {
		return teamGame;
	}
	
	public int getWinnerID() {
		return winnerID;
	}
	
	public List<PlayerResults> getResults() {
		return results;
	}
}
