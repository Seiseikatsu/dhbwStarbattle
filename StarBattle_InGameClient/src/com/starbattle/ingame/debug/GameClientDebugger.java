package com.starbattle.ingame.debug;

import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameClientConnection;

public class GameClientDebugger {

	private GameClientConnection client;

	public GameClientDebugger() {
		client = new InGameClient();

		InGameClient.DEBUG_MODE = true;

		try {
			client.openInGameClient(new DebugSettings());
		} catch (GameClientException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GameClientDebugger();
	}

}
