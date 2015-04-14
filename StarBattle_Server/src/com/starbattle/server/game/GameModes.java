package com.starbattle.server.game;

public enum GameModes {

	TEAMDEATHMATCH("Team Deathmatch",true),
	DEATHMATCH("Deathmatch",false),
	CAPTURETHEFLAG("Capture the Flag",true);
	
	private String name;
	private boolean isTeamMode;
	
	private GameModes(String name,boolean isTeamMode)
	{
		this.name=name;
		this.isTeamMode=isTeamMode;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isTeamMode() {
		return isTeamMode;
	}
	
}
