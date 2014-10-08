package com.starbattle.network.server;

import com.esotericsoftware.kryonet.Connection;

public class PlayerConnection extends Connection{

	private String playerName=null;
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public boolean isPlayerRegistered()
	{
		if(playerName==null)
		{
			return false;
		}
		return true;
	}
	
}
