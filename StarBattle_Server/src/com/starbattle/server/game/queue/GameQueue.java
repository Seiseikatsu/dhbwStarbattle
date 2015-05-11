package com.starbattle.server.game.queue;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.game.GameInitializationFactory;
import com.starbattle.server.game.GameModes;

public class GameQueue {

	private List<PlayerConnection> players = new ArrayList<PlayerConnection>();
	private List<String> displayNames = new ArrayList<String>();
	private int targetPlayerCount;
	private String mapName;
	private GameModes mode;
	private GameStartListener gameStartListener;

	public GameQueue(GameStartListener gameStartListener) {
		this.gameStartListener = gameStartListener;
	}

	private BattleInitialization generateInitialization() {
		GameInitializationFactory factory = new GameInitializationFactory();
		for (int i = 0; i < targetPlayerCount; i++) {
			String displayName = displayNames.get(i);
			factory.addPlayer(players.get(i), displayName);
		}

		factory.initGameMode(GameModes.TEAMDEATHMATCH, 10);
		factory.setMap("testmap");
		return factory.build();
	}

	private void startGame() {
		BattleInitialization battleInitialization = generateInitialization();
		gameStartListener.startGame(battleInitialization,this);
	}
	
	public void removePlayer(PlayerConnection player)
	{
		int index=players.indexOf(player);
		if(index!=-1)
		{
			players.remove(index);
			displayNames.remove(index);
		}
	}

	public void addPlayer(PlayerConnection player, String displayName) {
		this.players.add(player);
		this.displayNames.add(displayName);
		// check for game start
		int anz = players.size();
		if (anz == targetPlayerCount) {
			// start game
			startGame();
		}
	}

	public boolean matchesSearchSettings(GameModes mode, String mapName, int playerCount) {
		if (mode != null) {
			if (mode != this.mode) {
				// wrong game mode!
				return false;
			}
		}
		if (mapName != null) {
			if (!mapName.equals(this.mapName)) {
				// wrong map!
				return false;
			}
		}
		if (playerCount > 0) {
			if (playerCount != targetPlayerCount) {
				// wrong player count!
				return false;
			}
		}
		return true;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public void setMode(GameModes mode) {
		this.mode = mode;
	}

	public void setTargetPlayerCount(int targetPlayerCount) {
		this.targetPlayerCount = targetPlayerCount;
	}

}
