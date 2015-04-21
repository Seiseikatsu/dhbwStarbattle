package com.starbattle.ingame.debug;

import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameClientDebugger {

	private GameClientConnection client;

	public GameClientDebugger() {
		client = new InGameClient();

		InGameClient.DEBUG_MODE = true;

		NP_PrepareGame prepare = createDebugInit();
		DebugConnection connection = new DebugConnection(client);
		try {
			client.openInGameClient(new DebugSettings(), prepare, connection);
		} catch (GameClientException e) {
			e.printStackTrace();
		}

	}

	private NP_PrepareGame createDebugInit() {
		NP_PrepareGame p = new NP_PrepareGame();
		p.mapName = "testmap";

		return p;
	}

	public static void main(String[] args) {
		new GameClientDebugger();
	}

}
