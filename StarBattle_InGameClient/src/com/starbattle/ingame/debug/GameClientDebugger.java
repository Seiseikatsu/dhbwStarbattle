package com.starbattle.ingame.debug;

import com.starbattle.debug.game.GameDebugger;
import com.starbattle.debug.game.PlayerList;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.main.InGameClient;

public class GameClientDebugger {

	public GameClientDebugger() throws GameClientException {

		PlayerList player = new PlayerList();
		player.addTestPlayer("TimoTester", "SuperTimo");
		player.addTestPlayer("HansHelfer", "SuperHans");
		//player.addTestPlayer("Pibi", "Bobo");
		String map="debugmap";
		
		InGameClient.DEBUG_MODE=false;
		GameDebugger debugger = new GameDebugger();
		debugger.create(map,player);

	}

	public static void main(String[] args) {
		try {
			new GameClientDebugger();
		} catch (GameClientException e) {
			e.printStackTrace();
		}
	}

}
