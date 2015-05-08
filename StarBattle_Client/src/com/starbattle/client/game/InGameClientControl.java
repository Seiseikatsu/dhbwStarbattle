package com.starbattle.client.game;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkGameListener;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.ingame.debug.DebugSettings;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.ingame.settings.GameclientSettings;
import com.starbattle.network.connection.objects.game.NP_GameEnd;
import com.starbattle.network.connection.objects.game.NP_GameException;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class InGameClientControl {

	private GameClientConnection game;
	private GameclientSettings settings;
	private boolean gameOpen;
	private NetworkConnection networkConnection;
	private StarBattleClient client;

	public InGameClientControl(StarBattleClient client) {

		this.client = client;
		this.networkConnection = client.getConnection();
		networkConnection.setGameListener(createReceiveListener());
		settings = new DebugSettings();
	}

	public void openGame(final NP_PrepareGame gameInfo) {
	    Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					game = new InGameClient();
					game.openInGameClient(settings, gameInfo, createSendConnection());

				} catch (GameClientException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();		
	}

	private NetworkGameListener createReceiveListener() {
		return new NetworkGameListener() {

			@Override
			public void receivedGameUpdate(NP_GameUpdate update) {
				game.receivedObject(update);
			}

			@Override
			public void receivedGameStart(NP_GameStart start) {
				game.receivedObject(start);
			}

			@Override
			public void receivedGameException(NP_GameException exception) {
				game.receivedObject(exception);
			}

			@Override
			public void receivedGameEnd(NP_GameEnd end) {
				game.receivedObject(end);
			}

			@Override
			public void receivedPrepareGame(NP_PrepareGame prepareGame) {
				// open the ingame client and hide client
				// client.getWindow().getWindow().dispose();
				openGame(prepareGame);
				System.out.println("Received GamePreprare: "+prepareGame);
			}
		};
	}

	private GameSendConnection createSendConnection() {
		return new GameSendConnection() {
			@Override
			public void sendUDP(Object udp) {
				networkConnection.getSendConnection().sendUDP(udp);
			}

			@Override
			public void sendTCP(Object tcp) {
				System.out.println("Client send: " + tcp);
				networkConnection.getSendConnection().sendTCP(tcp);
			}
		};
	}

	public void closeGame() {
		game.closeInGameClient();
	}

	public void reconnectedPlayer() {
		game.reconnected();
	}

	public void disconnectedPlayer() {
		game.connectionLost();
	}

	public boolean isGameOpen() {
		return gameOpen;
	}

	public GameclientSettings getSettings() {
		return settings;
	}
}
