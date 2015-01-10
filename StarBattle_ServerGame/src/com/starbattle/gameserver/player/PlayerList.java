package com.starbattle.gameserver.player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerList {

	
	private ArrayList<GamePlayer> player=new ArrayList<GamePlayer>();
	private HashMap<String, Integer> playerIDs=new HashMap<String,Integer>();
	
	public PlayerList()
	{
		
	}
	

	
	public ArrayList<GamePlayer> getPlayer() {
		return player;
	}
	
	public HashMap<String, Integer> getPlayerIDs() {
		return playerIDs;
	}
	
	public GamePlayer getPlayer(int id)
	{
		return player.get(id);
	}
	
}
