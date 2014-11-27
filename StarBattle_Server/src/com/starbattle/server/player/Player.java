package com.starbattle.server.player;

import com.starbattle.network.server.PlayerConnection;

public class Player {

	private PlayerConnection connection;
	private String displayName;
	private boolean inGame=false;
	
	
	public Player(PlayerConnection player, String displayName)
	{
		this.displayName=displayName;
		connection=player;
	}
	
	public String getDisplayName() {
		return displayName;
	}	
	
	public boolean isInGame() {
		return inGame;
	}
	
	public PlayerConnection getConnection() {
		return connection;
	}
}
