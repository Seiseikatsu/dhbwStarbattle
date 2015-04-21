package com.starbattle.gameserver.player.container;

import java.util.ArrayList;
import java.util.HashMap;

import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.player.GamePlayer;

public class PlayerList {

	
	private ArrayList<GamePlayer> players=new ArrayList<GamePlayer>();
	private HashMap<String, Integer> playerIDs=new HashMap<String,Integer>();
	private PlayerRespawnListener respawnListener;
	
	public PlayerList(PlayerRespawnListener respawnListener)
	{
		this.respawnListener=respawnListener;
	}
	
	
	public void initPlayer(BattleParticipant participant)
	{
		int playerID=playerIDs.size();
		String playerName=participant.getDisplayName();
		GamePlayer player=new GamePlayer(playerName, playerID);
		addPlayer(player);		
	}
	
	public void addPlayer(GamePlayer player)
	{
		player.setRespawnListener(respawnListener);
		int id=players.size();
		players.add(player);
		playerIDs.put(player.getAttributes().getPlayerName(), id);
	}
	
	public ArrayList<GamePlayer> getPlayers() {
		return players;
	}
	
	public HashMap<String, Integer> getPlayerIDs() {
		return playerIDs;
	}
	
	public GamePlayer getPlayer(int id)
	{
		return players.get(id);
	}
	
}
