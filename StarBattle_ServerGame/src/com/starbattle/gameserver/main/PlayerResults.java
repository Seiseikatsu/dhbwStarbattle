package com.starbattle.gameserver.main;

import com.starbattle.gameserver.player.GamePlayer;

public class PlayerResults {

	private String displayName;
	private int points,team;
	

	public PlayerResults(GamePlayer p) {
		this.displayName=p.getAttributes().getPlayerName();
		this.points=p.getAttributes().getPoints();
		this.team=p.getAttributes().getTeam().getTeamId();
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getTeam() {
		return team;
	}
}
