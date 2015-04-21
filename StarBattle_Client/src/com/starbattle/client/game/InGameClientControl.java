package com.starbattle.client.game;

import com.starbattle.ingame.debug.DebugSettings;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.settings.GameclientSettings;

public class InGameClientControl {

	private GameClientConnection game;
	private GameclientSettings settings;
	private boolean gameOpen;

	public InGameClientControl() {

		settings = new DebugSettings();
	}

	public void openGame() {
	/** TODO	
	 * try {
			game.openInGameClient(settings);
		} catch (GameClientException e) {
			e.printStackTrace();
		}**/
	}

	public void closeGame() {
		game.closeInGameClient();
	}

	public void reconnectedPlayer() {
		game.reconnected();
	}

	public void disconnectedPlayer() {
		game.connectionLost();
	}
	
	public void receiveGameUpdates(Object object)
	{
		game.receivedObject(object);
	}

	public boolean isGameOpen() {
		return gameOpen;
	}

	public GameclientSettings getSettings() {
		return settings;
	}
}
