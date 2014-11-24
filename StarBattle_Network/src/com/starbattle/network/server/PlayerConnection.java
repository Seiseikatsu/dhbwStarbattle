package com.starbattle.network.server;

import com.esotericsoftware.kryonet.Connection;

public class PlayerConnection extends Connection{

	private String accountName=null;
		
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
	
}
