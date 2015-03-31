package com.starbattle.gameserver.server;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.network.connection.ConnectionListener;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.server.PlayerConnection;

public class DefaultPlayerConnection implements ConnectionListener {

	private PlayerUpdateListener playerUpdate;

	public DefaultPlayerConnection(PlayerUpdateListener update) {
		this.playerUpdate = update;
	}

	@Override
	public void onConnect(Connection connection) {
		// player connected
		PlayerConnection player = (PlayerConnection) connection;
		playerUpdate.playerConnected(player.getAccountName());
	}

	@Override
	public void onDisconnect(Connection connection) {

		// player disconnected
		PlayerConnection player = (PlayerConnection) connection;
		playerUpdate.playerDisonnected(player.getAccountName());
	}

	@Override
	public void onReceive(Connection connection, Object object) {
		PlayerConnection player = (PlayerConnection) connection;
		String accountName = player.getAccountName();
		if (object instanceof NP_PlayerUpdate) {
			//player update
			playerUpdate.receivedPlayerUpdate((NP_PlayerUpdate) object, accountName);
		}
	}

}
