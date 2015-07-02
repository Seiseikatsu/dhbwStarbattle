package com.starbattle.client.game;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkGameListener;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.play.ingame.InGameView;
import com.starbattle.ingame.debug.DebugSettings;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.ingame.settings.GameclientSettings;
import com.starbattle.network.connection.objects.NP_BattleResults;
import com.starbattle.network.connection.objects.NP_ExitGame;
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
	private Thread clientThread;
	private NP_PrepareGame gameInfo;
	private boolean startGame = false;
	
	public static int PLAYER_ID;
	
	
	public InGameClientControl(StarBattleClient clientt) {

		this.client = clientt;
		this.networkConnection = client.getConnection();
		networkConnection.setGameListener(createReceiveListener());
		settings = new DebugSettings();
		Thread clientThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					while (true) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (startGame) {
							startGame = false;
							game = new InGameClient();
							game.openInGameClient(settings, gameInfo, createSendConnection());
							System.out.println("Closed Game");		
							if(!game.gameEnded())//only if the game didnt end itself 
							{
							//open lobby view						
							client.getWindow().getContent().showView(LobbyView.VIEW_ID);
							//send server I quit the game
							client.getConnection().getSendConnection().sendTCP(new NP_ExitGame());
							}
						}
					}
				} catch (GameClientException e) {
					e.printStackTrace();
				}
			}
		});
		clientThread.start();
	}

	public void openGame(final NP_PrepareGame gameInfo) {
		// set client view
		System.out.println("OPEN GAME!");
		PLAYER_ID=gameInfo.playerID;
		client.getWindow().getContent().showView(InGameView.VIEW_ID);
		this.gameInfo=gameInfo;
		startGame=true;
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
				System.out.println("-------------> GaemEnd");
				game.receivedObject(end);
				
			}

			@Override
			public void receivedPrepareGame(NP_PrepareGame prepareGame) {
				
				openGame(prepareGame);
				System.out.println("Received GamePreprare: " + prepareGame);
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
