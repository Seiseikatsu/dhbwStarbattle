package com.starbattle.server.game.queue;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.main.BattleInitialization;
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

	public MatchQueueManager(GameManager gameManager, PlayerContainer playerContainer) {
		this.gameManager = gameManager;
		this.playerContainer = playerContainer;
	}

	private List<PlayerConnection> waitingPlayers = new ArrayList<PlayerConnection>();

	public void playerEnteredQueue(PlayerConnection pl, NP_EnterMatchQueue settings) {

		waitingPlayers.add(pl);

		if (waitingPlayers.size() > 1) {
			// debug start game for player
			GameInitializationFactory factory = new GameInitializationFactory();
			for (PlayerConnection player : waitingPlayers) {
				String displayName = playerContainer.getPlayer(player.getAccountName()).getDisplayName();
				factory.addPlayer(player, displayName);
			}

			factory.initGameMode(GameModes.TEAMDEATHMATCH, 10);
			factory.setMap("testmap");
			BattleInitialization init = factory.build();
			// start game
			int gameID = gameManager.openGame(init);
			// send game prepare to players
			NP_PrepareGame prepare = GamePrepareConvertor.createPrepareGame(init);

			for ( int i=0; i<waitingPlayers.size(); i++) {
				prepare.playerID=i;
				PlayerConnection player=waitingPlayers.get(i);
				player.setGameID(gameID);
				player.sendTCP(prepare);
			}
		}

	}

	public void playerLeftQueue(PlayerConnection player) {

	}
}
