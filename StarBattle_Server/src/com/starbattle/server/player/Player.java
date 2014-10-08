package com.starbattle.server.player;

import com.starbattle.network.server.PlayerConnection;

public class Player {

	private PlayerConnection connection;
	private boolean inGame=false;
	
	
	public Player(PlayerConnection player)
	{
		connection=player;
	}
	
	
	
	public boolean isInGame() {
		return inGame;
	}
	
	public PlayerConnection getConnection() {
		return connection;
	}
}
