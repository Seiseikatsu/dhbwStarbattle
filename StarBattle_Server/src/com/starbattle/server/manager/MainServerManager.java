package com.starbattle.server.manager;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.network.connection.ConnectionListener;
import com.starbattle.network.connection.objects.NP_ChatMessage;
import com.starbattle.network.connection.objects.NP_FriendRequest;
import com.starbattle.network.connection.objects.NP_HandleFriendRequest;
import com.starbattle.network.connection.objects.NP_Login;
import com.starbattle.network.connection.objects.NP_Logout;
import com.starbattle.network.connection.objects.NP_Register;
import com.starbattle.network.connection.objects.NP_ResetEmail;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.server.NetworkServer;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.player.PlayerContainer;

public class MainServerManager {

	private NetworkServer server;
	private PlayerManager playerManager;
	private PlayerContainer playerContainer;
	private GameManager gameManager;

	public MainServerManager(NetworkServer server) {
		this.server = server;
		playerContainer = new PlayerContainer();
		playerManager = new PlayerManager(playerContainer);
		gameManager = new GameManager();
	}

	public ConnectionListener createListener() {
		return new ConnectionListener() {

			@Override
			public void onDisconnect(Connection connection) {

				clientDisconnected(connection);
			}

			@Override
			public void onConnect(Connection connection) {

				clientConnected(connection);

			}

			@Override
			public void onReceive(Connection connection, Object object) {

				receivedObject(connection, object);
			}
		};
	}

	private void clientConnected(Connection connection) {
		System.out.println("New Client (" + connection.getID() + ") connected to Server!");
	}

	private void clientDisconnected(Connection connection) {
		// remove from login player list
		System.out.println("Lost Connection to Client " + connection.getID());
		playerManager.logoutPlayer((PlayerConnection) connection);
	}

	private void receivedObject(Connection connection, Object object) {
		PlayerConnection player = (PlayerConnection) connection;
		if (object instanceof NP_Login) {
			playerManager.tryLogin(player, (NP_Login) object);
		} else if (object instanceof NP_Register) {
			playerManager.tryRegister(player, (NP_Register) object);
		} else if (object instanceof NP_Logout) {
			playerManager.logoutPlayer(player);
		} else if (object instanceof NP_ResetEmail) {
			playerManager.tryResetEmail((NP_ResetEmail) object);
		} else if (object instanceof NP_FriendRequest) {
			playerManager.trySendFriendRequest(player, (NP_FriendRequest) object);
		} else if (object instanceof NP_HandleFriendRequest) {
			playerManager.handleFriendRequest(player, (NP_HandleFriendRequest) object);
		} else if (object instanceof NP_ChatMessage) {
			playerManager.sendChat(player, (NP_ChatMessage) object);
		} else if (object instanceof NP_PlayerUpdate) {
			gameManager.receivedPlayerUpdate((NP_PlayerUpdate) object, player);
		}
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}
}
