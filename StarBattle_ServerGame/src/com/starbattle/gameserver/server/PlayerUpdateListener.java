package com.starbattle.gameserver.server;

import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public interface PlayerUpdateListener {

	public void receivedPlayerUpdate(NP_PlayerUpdate data, String accountName);
	
	public void playerConnected(String accountName);
	
	public void playerDisonnected(String accountName);
	
}
