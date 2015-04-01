package com.starbattle.gameserver.game;

import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class GameConnection  {

	private GameContainer game;
	
	public  GameConnection(GameContainer game) {
		this.game=game;
	}
	

	public void playerConnected(String accountName) {
		
	}


	public void playerDisonnected(String accountName) {
		
	}

	
	public void receivedPlayerUpdate(NP_PlayerUpdate data, String accountName) {
		
	}
	
	public NP_GameUpdate getGameUpdate()
	{
			return null;
	}

}
