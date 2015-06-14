package com.starbattle.gameserver.main;


import java.util.List;

import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.server.PlayerConnection;

public interface StarbattleGame {

	public void startBattle(BattleInitialization battleInitialization, BattleEndListener battleEndListener);
	
	public NP_GameUpdate getGameUpdate();
	
	public void updatePlayer(NP_PlayerUpdate update, String accountName);
	
	public void playerConnected(String accountName);
	
	public boolean playerDisconnected(String accountName);
	
	public void updateGame(double delta);
	
	public void sendToAllPlayersUDP(Object object);
	
	public void sendToAllPlayersTCP(Object object);
	
	public List<PlayerConnection> getPlayers();
	
	public boolean isRunning();
	
}
