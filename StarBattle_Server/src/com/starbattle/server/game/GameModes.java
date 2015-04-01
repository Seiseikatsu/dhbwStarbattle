package com.starbattle.server.game;

public enum GameModes {

	TEAMDEATHMATCH(true),DEATHMATCH(false),CAPTURETHEFLAG(true);
	
	private boolean isTeamMode;
	
	private GameModes(boolean isTeamMode)
	{
		this.isTeamMode=isTeamMode;
	}
	
	
	public boolean isTeamMode() {
		return isTeamMode;
	}
	
}
