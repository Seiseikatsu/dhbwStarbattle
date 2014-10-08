package com.starbattle.server.player;

import java.util.HashMap;

import com.starbattle.network.server.PlayerConnection;

public class PlayerContainer {

	private HashMap<String,Player> player=new HashMap<String,Player>();
	
	public  PlayerContainer()
	{
		
	}
	
	public void loginPlayer(PlayerConnection connection)
	{
		player.put(connection.getPlayerName(), new Player(connection));
	}
	
	public void logoutPlayer(Player player)
	{
		this.player.remove(player);
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
	
	
}
