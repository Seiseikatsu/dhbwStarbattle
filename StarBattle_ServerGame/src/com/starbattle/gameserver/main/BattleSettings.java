package com.starbattle.gameserver.main;

import com.starbattle.gameserver.game.mode.GameMode;

public class BattleSettings {

	private GameMode gameMode;
	private String mapName;
	

	public BattleSettings() {

	}
	
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMapName() {
		return mapName;
	}
	
	public GameMode getMode() {
		return gameMode;
	}

}
