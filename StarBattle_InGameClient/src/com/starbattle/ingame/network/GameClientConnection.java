package com.starbattle.ingame.network;

import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.settings.GameclientSettings;

public interface GameClientConnection {

	
	public void openInGameClient(GameclientSettings settings) throws GameClientException;
	
	public void receivedObject(Object object);
	
	public void connectionLost();
	
	public void reconnected();
	
	public void closeInGameClient();
		
	
}
