package com.starbattle.gameserver.server;

import com.starbattle.gameserver.exceptions.ServerStartException;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;

public interface GameServerInterface {

	public void setReceiveListener(PlayerUpdateListener playerUpdateListener);
	
	public void sendGameUpdate(NP_GameUpdate update);
	
	public void openConnection() throws ServerStartException;
	
	public void closeConnection();
	
	public ConnectionDetails getConnection();
	
}
