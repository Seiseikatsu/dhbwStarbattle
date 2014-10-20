package com.starbattle.server.manager;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.network.connection.ConnectionListener;
import com.starbattle.network.server.NetworkServer;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.network.connection.objects.*;
import com.starbattle.server.player.PlayerContainer;

public class MainServerManager {

	private NetworkServer server;
	private PlayerManager playerManager;
	private PlayerContainer playerContainer;

	public MainServerManager(NetworkServer server) {
		this.server = server;
		playerContainer = new PlayerContainer();
		playerManager = new PlayerManager(server.getSendConnection(), playerContainer);
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
		}  else if (object instanceof NP_ResetEmail) {
			playerManager.tryResetEmail((NP_ResetEmail)object);
		}
	}
}
