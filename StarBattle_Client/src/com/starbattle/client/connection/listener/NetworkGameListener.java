package com.starbattle.client.connection.listener;

import com.starbattle.network.connection.objects.NP_BattleResults;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_GameEnd;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_GameException;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;


public interface NetworkGameListener {

	public void receivedGameUpdate(NP_GameUpdate update);
	
	public void receivedGameStart(NP_GameStart start);
	
	public void receivedGameEnd(NP_GameEnd end);
	
	public void receivedGameException(NP_GameException exception);

	public void receivedPrepareGame(NP_PrepareGame object);

}
