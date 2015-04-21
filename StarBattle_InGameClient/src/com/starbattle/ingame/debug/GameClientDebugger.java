package com.starbattle.ingame.debug;

import com.starbattle.debug.game.GameDebugger;
import com.starbattle.debug.game.PlayerList;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameClientDebugger {

	public GameClientDebugger() throws GameClientException {

		PlayerList player = new PlayerList();
		player.addTestPlayer("TimoTester", "SuperTimo");

		GameDebugger debugger = new GameDebugger();
		debugger.create(player);

	}

	public static void main(String[] args) {
		try {
			new GameClientDebugger();
		} catch (GameClientException e) {
			e.printStackTrace();
		}
	}

}
