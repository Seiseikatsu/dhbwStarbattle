package com.starbattle.ingame.network;

import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.settings.GameclientSettings;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public interface GameClientConnection {

	
	public void openInGameClient(GameclientSettings settings,NP_PrepareGame prepare, GameSendConnection gameSendConnection) throws GameClientException;
	
	public void receivedObject(Object object);
	
	public void connectionLost();
	
	public void reconnected();

	public boolean gameEnded();
	
}
