package com.starbattle.gameserver.game;

import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.map.ServerMap;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.PlayerAccount;

public class GameContainer {

	private PlayerAccount playerList;
	private ServerMap serverMap;
	private GameMode gameMode;
	private GameConnection gameUpdate;


	public GameContainer() {
		


	}

	public void startGame() {
		// setup objects
		playerList = new PlayerAccount(new PlayerRespawnListener() {
			@Override
			public void playerRespawned(GamePlayer player) {
				gameMode.onPlayerRespawn(player);
			}
		});
		gameUpdate=new GameConnection(this);
	}
	
	public void updateGame(double delta) {

	}
	
	public GameConnection getGameUpdate() {
		return gameUpdate;
	}

	public PlayerAccount getPlayerList() {
		return playerList;
	}

	public ServerMap getServerMap() {
		return serverMap;
	}
}
