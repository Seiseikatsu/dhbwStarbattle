package com.starbattle.gameserver.main;

import java.util.List;

import com.starbattle.gameserver.game.mode.GameMode;

public class BattleSettings {

	private GameMode mode;
	
	/*
	 * TODO: Add player
	 */
	
	private List<String> accountNames;
	
	
	public boolean isPlayerInGame(String accountName)
	{
		return accountNames.contains(accountName);
	}
}
