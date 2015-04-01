package com.starbattle.server.manager;

import java.util.HashMap;

import com.starbattle.gameserver.game.timer.GameLoop;
import com.starbattle.gameserver.game.timer.UpdateListener;
import com.starbattle.gameserver.main.BattleEndListener;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleResults;
import com.starbattle.gameserver.main.BattleSettings;
import com.starbattle.gameserver.main.StarbattleGame;
import com.starbattle.gameserver.main.StarbattleGameControl;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.server.PlayerConnection;

public class GameManager {

	private HashMap<Integer, StarbattleGame> games = new HashMap<Integer, StarbattleGame>();
	private GameLoop gameLoop, updateLoop;
	private int gameIdCount;

	public GameManager() {
		initGames();
	}

	private void initGames() {
		gameLoop = new GameLoop(new UpdateListener() {
			public void update(double delta) {
				// update all games
				updateGames(delta);
			}
		});
		gameLoop.setFPS(60);// Logical Update Units
		gameLoop.start();

		updateLoop = new GameLoop(new UpdateListener() {
			public void update(double delta) {

				// send game updates to all players
				sendGameUpdates();
			}
		});
		updateLoop.setFPS(30);
		updateLoop.start();
	}

	public void receivedPlayerUpdate(NP_PlayerUpdate update, PlayerConnection connection) {
		int gameID = connection.getGameID();
		if (gameID != PlayerConnection.IN_LOBBY) {
			StarbattleGame game = games.get(gameID);
			game.updatePlayer(update, connection.getAccountName());
		}
	}

	public void openGame(BattleInitialization battleInitialization) {

		final StarbattleGame game = new StarbattleGameControl(gameIdCount);
		game.startBattle(battleInitialization, new BattleEndListener() {
			@Override
			public void battleError() {

				removeGame(game);
			}

			@Override
			public void battleEnd(BattleResults results) {

				removeGame(game);
			}
		});
		gameIdCount++;
	}

	private void updateGames(double delta) {
		for (StarbattleGame game : games.values()) {
			game.updateGame(delta);
		}
	}

	private void sendGameUpdates() {
		for (StarbattleGame game : games.values()) {
			NP_GameUpdate update = game.getGameUpdate();
			// send to all players from the game
			game.sendToAllPlayersUDP(update);
		}
	}

	private void removeGame(StarbattleGame game) {
		games.remove(game);
	}

}
