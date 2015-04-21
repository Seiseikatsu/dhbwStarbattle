package com.starbattle.ingame.debug;

import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.network.connection.objects.game.NP_ClientReady;
import com.starbattle.network.connection.objects.game.NP_GameStart;

public class DebugConnection implements GameSendConnection {

	private GameClientConnection client;

	public DebugConnection(GameClientConnection client) {
		this.client = client;
	}

	@Override
	public void send(Object object) {
		System.out.println("Send Important Update:  " + object.getClass().getSimpleName());
		if (object instanceof NP_ClientReady) {
			// Send Game Start to client
			client.receivedObject(new NP_GameStart());
		}
	}

}
