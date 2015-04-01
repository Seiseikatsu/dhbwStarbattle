package com.starbattle.network.server;

import com.esotericsoftware.kryonet.Connection;

public class PlayerConnection extends Connection{

	public final static int IN_LOBBY=-1;
	private String accountName=null;
	private int gameID=IN_LOBBY;
		
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String playerName) {
		this.accountName = playerName;
	}
	
	public boolean isPlayerRegistered()
	{
		if(accountName==null)
		{
			return false;
		}
		return true;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	
}
