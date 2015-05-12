package com.starbattle.server.modes;

import java.util.List;

public class ModeSettingsEntry {

	private GameModes mode;
	private List<String> maps;
	
	public List<String> getMaps() {
		return maps;
	}
	
	public GameModes getMode() {
		return mode;
	}
	
	public void setMaps(List<String> maps) {
		this.maps = maps;
	}
	
	public void setMode(GameModes mode) {
		this.mode = mode;
	}	
}
