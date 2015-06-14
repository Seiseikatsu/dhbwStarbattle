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
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.modes.GameModeContainer;

public class GameManager {

	private GameModeContainer gameModes = new GameModeContainer();
	private HashMap<Integer, StarbattleGame> games = new HashMap<Integer, StarbattleGame>();
	private HashMap<Integer, GameLoading> gameLoader = new HashMap<Integer, GameLoading>();

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

	public void playerExit(PlayerConnection player) {
		int gameID = player.getGameID();
		if (gameID != PlayerConnection.IN_LOBBY) {
			StarbattleGame game = games.get(gameID);
			boolean stopGame = game.playerDisconnected(player.getAccountName());
			if (stopGame) {
				removeGame(gameID);
			}
		}
	}

	public void playerReady(PlayerConnection player) {
		int gameID = player.getGameID();
		if (gameLoader.containsKey(gameID)) {
			// tell gameLoader player has finished loading
			gameLoader.get(gameID).playerFinishedLoading(player);
		}
	}

	public int openGame(final BattleInitialization battleInitialization) {

		final int gameID = gameIdCount;
		Thread openGameThread = new Thread(new Runnable() {

			@Override
			public void run() {

				final StarbattleGame game = new StarbattleGameControl(gameID);
				game.startBattle(battleInitialization, new BattleEndListener() {
					@Override
					public void battleError() {

						removeGame(gameID);
					}

					@Override
					public void battleEnd(BattleResults results) {

						removeGame(gameID);
					}
				});

				// add game
				games.put(gameID, game);
				// create loader
				GameLoading loader = new GameLoading(battleInitialization);
				gameLoader.put(gameID, loader);
				gameIdCount++;
			}
		});
		openGameThread.start();
		System.out.println("GameManager: Open Game " + gameID);

		return gameID;
	}

	private void updateGames(double delta) {
		for (StarbattleGame game : games.values()) {
			if (game.isRunning()) {
				game.updateGame(delta);
			}
		}
	}

	private void sendGameUpdates() {
		for (StarbattleGame game : games.values()) {
			if (game.isRunning()) {
				NP_GameUpdate update = game.getGameUpdate();
				// send to all players from the game
				game.sendToAllPlayersUDP(update);
			}
		}
	}

	private void removeGame(int id) {
		// set all players free from the game
		for (PlayerConnection player : games.get(id).getPlayers()) {
			player.setGameID(PlayerConnection.IN_LOBBY);
		}
		gameLoader.remove(id);
		games.remove(id);
		System.out.println("GameManager: Remove Game " + id);
	}

	public void close() {
		// stop game and update loops
		gameLoop.stop();
		updateLoop.stop();
	}

	public int getNumberOfGames() {
		return games.size();
	}

	public GameModeContainer getGameModes() {
		return gameModes;
	}

}
