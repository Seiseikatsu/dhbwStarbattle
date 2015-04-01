package com.starbattle.gameserver.server;

import com.starbattle.gameserver.main.StarbattleGameControl;

public class GameConnectionResolver {

	private StarbattleGameControl game;
	private PlayerUpdateListener playerUpdate;
	
	public GameConnectionResolver(StarbattleGameControl game) {
		this.game = game;
	}
	
	public void setPlayerUpdate(PlayerUpdateListener update)
	{
		this.playerUpdate=update;
	}
	
	public PlayerUpdateListener getPlayerUpdate() {
		return playerUpdate;
	}

	public boolean isPlayerInGame(String accountName) {
		return game.getBattleSettings().isPlayerInGame(accountName);
	}

	public StarbattleGameControl getGame() {
		return game;
	}
}
