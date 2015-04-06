package com.starbattle.server.player;

import java.util.HashMap;

import com.starbattle.network.server.PlayerConnection;

public class PlayerContainer {

	private HashMap<String,Player> player=new HashMap<String,Player>();
	
	public  PlayerContainer()
	{
		
	}
	
	public void loginPlayer(PlayerConnection connection, String displayName)
	{
		player.put(connection.getAccountName(), new Player(connection,displayName));
	}
	
	public void logoutPlayer(String playerName)
	{
		this.player.remove(playerName);
	}
	
	public HashMap<String, Player> getPlayer() {
		return player;
	}
	
	public Player getPlayer(String name)
	{
		return player.get(name);
	}
	
	public boolean playerConnected(String name)	
	{
		return player.containsKey(name);
	}
	
	public int getNumberOfPlayers()
	{
		return player.size();
	}
	
	
}
