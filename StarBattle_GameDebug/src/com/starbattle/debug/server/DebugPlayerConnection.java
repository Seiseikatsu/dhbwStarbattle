package com.starbattle.debug.server;

import com.starbattle.network.server.PlayerConnection;

public class DebugPlayerConnection extends PlayerConnection {

	private SendClientsConnection clientConnection;

	public DebugPlayerConnection(SendClientsConnection sendConnection) {
		this.clientConnection = sendConnection;
	}

	@Override
	public int sendTCP(Object object) {

		clientConnection.sendToClient(getAccountName(), object);
		return 0;
	}

	@Override
	public int sendUDP(Object object) {

		clientConnection.sendToClient(getAccountName(), object);

		return 0;
	}

}
