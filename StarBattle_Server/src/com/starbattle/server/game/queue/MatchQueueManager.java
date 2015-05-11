package com.starbattle.server.game.queue;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.network.connection.objects.NP_EnterMatchQueue;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.game.GameInitializationFactory;
import com.starbattle.server.game.GameModes;
import com.starbattle.server.game.GamePrepareConvertor;
import com.starbattle.server.manager.GameManager;
import com.starbattle.server.player.PlayerContainer;

public class MatchQueueManager {

	private GameManager gameManager;
	private PlayerContainer playerContainer;
	private GameStartListener gameLauncher;
	private List<GameQueue> queues = new ArrayList<GameQueue>();

	public MatchQueueManager(GameManager gameManager, PlayerContainer playerContainer) {
		this.gameManager = gameManager;
		this.playerContainer = playerContainer;
		gameLauncher = new GameLauncher();
	}

	// private List<PlayerConnection> waitingPlayers = new
	// ArrayList<PlayerConnection>();

	private synchronized GameQueue createNewQueue(String mapName, int playerCount, GameModes mode) {
		if (playerCount == 0) {
			playerCount = mode.getDefaultPlayerCount();
		}
		if (mapName == null) {
			mapName = mode.getDefaultMap();
		}
		GameQueue queue = new GameQueue(gameLauncher);
		queue.setMapName(mapName);
		queue.setMode(mode);
		queue.setTargetPlayerCount(playerCount);
		// add to queues
		queues.add(queue);
		return queue;
	}

	private synchronized GameQueue getMatchingQueue(NP_EnterMatchQueue settings) throws GameQueueException {
		String mapName = settings.mapName;
		int playerCount = settings.numberOfPlayers;
		String modeName = settings.modeName;
		// try to map modeName to enum
		try {
			GameModes mode = GameModes.valueOf(modeName);
			// search trough all queues
			for (GameQueue gameQueue : queues) {
				if (gameQueue.matchesSearchSettings(mode, mapName, playerCount)) {
					// found a queue
					return gameQueue;
				}
			}
			// create new queue because found none
			return createNewQueue(mapName, playerCount, mode);
		} catch (Exception e) {
			throw new GameQueueException("Could not find requested Mode!");
		}
	}

	public synchronized void playerEnteredQueue(PlayerConnection player, NP_EnterMatchQueue settings)
			throws GameQueueException {

		// check if player is not already in a queue or game
		if (player.isInQueue() || player.getGameID() != PlayerConnection.IN_LOBBY) {
			throw new GameQueueException("Player is not available!");
		}

		String displayName = playerContainer.getPlayer(player.getAccountName()).getDisplayName();
		// find matching queue
		GameQueue queue = getMatchingQueue(settings);
		player.setInQueue(true);
		// add player to queue
		queue.addPlayer(player, displayName);
	}

	public synchronized void playerLeftQueue(PlayerConnection player) {
		if (!player.isInQueue()) {
			//cancel if player is not in a queue
			return;
		}
		// find queue with the player and remove him
		for (GameQueue queue : queues) {
			queue.removePlayer(player);
		}
	}

	private class GameLauncher implements GameStartListener {

		@Override
		public void startGame(BattleInitialization init, GameQueue invoker) {

			// remove queue from list of current queues
			queues.remove(invoker);

			// create game prepare and start game
			List<BattleParticipant> participants = init.getBattleParticipants();
			int gameID = gameManager.openGame(init);
			// send game prepare to players
			NP_PrepareGame prepare = GamePrepareConvertor.createPrepareGame(init);

			for (int i = 0; i < participants.size(); i++) {
				prepare.playerID = i;
				PlayerConnection player = participants.get(i).getConnection();
				player.setGameID(gameID);
				player.setInQueue(false);
				player.sendTCP(prepare);
			}
		}

	}
}
