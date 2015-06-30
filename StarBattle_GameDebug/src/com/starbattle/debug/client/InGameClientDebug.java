package com.starbattle.debug.client;

import com.starbattle.ingame.debug.DebugSettings;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class InGameClientDebug {

	private GameClientConnection client;

	public InGameClientDebug() {
		client = new InGameClient();
	}

	public void open(NP_PrepareGame setup, GameSendConnection sendConnection) throws GameClientException {
		client.openInGameClient(new DebugSettings(), setup, sendConnection);
	}



	public void receiveObject(Object object) {
		client.receivedObject(object);
	}

}
