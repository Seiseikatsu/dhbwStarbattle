package com.starbattle.ingame.network;

import com.starbattle.network.connection.objects.game.NP_GameUpdate;

public interface ObjectReceiveListener
{
	
	public void updateGame(NP_GameUpdate message);

	public void startGame();

	
}
