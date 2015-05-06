package com.starbattle.gameserver.player.container;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.soap.SOAPBinding.ParameterStyle;

import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.map.SpawnPoint;
import com.starbattle.gameserver.map.SpawnPointList;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.network.connection.objects.game.NP_PlayerData;

public class PlayerList {

	private ArrayList<GamePlayer> players = new ArrayList<GamePlayer>();
	private HashMap<String, Integer> displayNames = new HashMap<String, Integer>();
	private HashMap<String, Integer> accountNames = new HashMap<String, Integer>();

	private PlayerRespawnListener respawnListener;

	public PlayerList() {
	}

	public void setRespawnListener(PlayerRespawnListener respawnListener) {
		this.respawnListener = respawnListener;
	}

	public void initPlayer(BattleParticipant participant, CollisionDetection collisionDetection, EffectTrigger effectTrigger) {
		System.out.println("Server: Added Player : " + participant.getAccountName());
		int playerID = displayNames.size();
		String playerName = participant.getDisplayName();
		GamePlayer player = new GamePlayer(playerName, playerID,collisionDetection,effectTrigger);
		player.getAttributes().setTeam(participant.getTeam());
		addPlayer(player, participant.getAccountName());
	}

	public void addPlayer(GamePlayer player, String accountName) {
		player.setRespawnListener(respawnListener);
		int id = players.size();
		players.add(player);
		displayNames.put(player.getAttributes().getPlayerName(), id);
		accountNames.put(accountName, id);
	}

	public GamePlayer getPlayer(String userName) {
		if (!displayNames.containsKey(userName)) {
			System.err.println("No Player mapped to Name: " + userName);
			return null;
		}
		int id = displayNames.get(userName);
		return players.get(id);
	}

	public GamePlayer getPlayerByAccount(String accountName) {
		int id = accountNames.get(accountName);
		return players.get(id);
	}

	public ArrayList<GamePlayer> getPlayers() {
		return players;
	}

	public HashMap<String, Integer> getPlayerIDs() {
		return displayNames;
	}

	public GamePlayer getPlayer(int id) {
		return players.get(id);
	}

	public NP_PlayerData[] generatePlayerData() {
		int size = players.size();
		NP_PlayerData[] data = new NP_PlayerData[size];
		for (int i = 0; i < size; i++) {
			data[i] = players.get(i).getData();
		}
		return data;
	}

	public void initSpawn(SpawnPointList spawnPoints) {

		for (GamePlayer player : players) {
			Team team = player.getAttributes().getTeam();
			System.out.println("Init Spawn: Player "+player.getAttributes().getPlayerName()+" / Team "+team);
			SpawnPoint spawnPoint = spawnPoints.getRandomSpawnPoint(team);
			
			player.teleportTo(spawnPoint.getLocation());
		}
	}

}
