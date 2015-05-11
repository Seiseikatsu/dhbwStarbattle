package com.starbattle.client.connection.listener;

import com.starbattle.network.connection.objects.NP_GameModesList;

public interface NetworkGameQueryListener {

	public void receivedGameModes(NP_GameModesList modes);
	
	public void receivedQueryCancel();
	
}
