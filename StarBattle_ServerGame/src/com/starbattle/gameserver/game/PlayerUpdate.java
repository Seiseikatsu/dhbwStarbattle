package com.starbattle.gameserver.game;

import com.starbattle.gameserver.server.PlayerUpdateListener;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class PlayerUpdate implements PlayerUpdateListener {

	private GameContainer game;
	
	public  PlayerUpdate(GameContainer game) {
		this.game=game;
	}
	


	@Override
	public void playerConnected(String accountName) {
		
	}

	@Override
	public void playerDisonnected(String accountName) {
		
	}

	@Override
	public void receivedPlayerUpdate(NP_PlayerUpdate data, String accountName) {
		
	}

}
