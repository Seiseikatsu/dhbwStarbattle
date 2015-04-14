package com.starbattle.server.game;

import java.util.ArrayList;
import java.util.List;

public class GameModeContainer {

	private List<PlayableGameMode> modes=new ArrayList<PlayableGameMode>();
	
	public GameModeContainer() {
		defaultInit();
	}
	
	private void defaultInit()
	{
		modes.add(new PlayableGameMode(GameModes.DEATHMATCH, "Testmap", 6));
		modes.add(new PlayableGameMode(GameModes.DEATHMATCH, "Testmap", 10));
		modes.add(new PlayableGameMode(GameModes.TEAMDEATHMATCH, "Testmap", 20));		
	}
	
	public void removeMode(int nr)
	{
		modes.remove(nr);
	}
	
	public void addMode(PlayableGameMode mode)
	{
		modes.add(mode);
	}
	
	public List<PlayableGameMode> getModes() {
		return modes;
	}
	
	
}
