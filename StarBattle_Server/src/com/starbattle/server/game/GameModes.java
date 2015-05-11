package com.starbattle.server.game;

public enum GameModes {

	TEAMDEATHMATCH("Team Deathmatch",true,"testmap",10),
	DEATHMATCH("Deathmatch",false,"testmap",5),
	CAPTURETHEFLAG("Capture the Flag",true,"testmap",10);
	
	private String name;
	private String defaultMap;
	private int defaultPlayerCount;
	private boolean isTeamMode;
	
	private GameModes(String name,boolean isTeamMode, String defaultMap, int defaultCount)
	{
		this.name=name;
		this.isTeamMode=isTeamMode;
		this.defaultMap=defaultMap;
		this.defaultPlayerCount=defaultCount;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isTeamMode() {
		return isTeamMode;
	}
	
	public String getDefaultMap() {
		return defaultMap;
	}
	
	public int getDefaultPlayerCount() {
		return defaultPlayerCount;
	}
}
