package com.starbattle.gameserver.game;

import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.map.ServerMap;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;

public class GameContainer {

	private PlayerList playerList;
	private ServerMap serverMap;
	private GameMode gameMode;
	private GameConnection gameUpdate;

	public GameContainer(BattleInitialization init) {

		// init mode
		gameMode = init.getBattleSettings().getMode();
		// init players
		for (BattleParticipant participant : init.getBattleParticipants()) {
			playerList.initPlayer(participant);
		}
	}

	public void startGame() {
		// setup objects
		playerList = new PlayerList(new PlayerRespawnListener() {
			@Override
			public void playerRespawned(GamePlayer player) {
				gameMode.onPlayerRespawn(player);
			}
		});
		gameUpdate = new GameConnection(this);
	}

	public void updateGame(double delta) {

	}

	public GameConnection getGameUpdate() {
		return gameUpdate;
	}

	public PlayerList getPlayerList() {
		return playerList;
	}

	public ServerMap getServerMap() {
		return serverMap;
	}
}
