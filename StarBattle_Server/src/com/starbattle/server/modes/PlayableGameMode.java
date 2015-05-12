package com.starbattle.server.modes;


public class PlayableGameMode {

	private GameModes mode;
	private String mapFile;
	private int player;
	
	public PlayableGameMode(GameModes mode, String map, int player) {
		this.mode=mode;
		this.mapFile=map;
		this.player=player;
	}
	
	public String getMapFile() {
		return mapFile;
	}
	
	public GameModes getMode() {
		return mode;
	}
	
	public int getPlayer() {
		return player;
	}
}
