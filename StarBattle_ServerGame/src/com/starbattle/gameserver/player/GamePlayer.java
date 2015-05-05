package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.map.SpawnPoint;
import com.starbattle.network.connection.objects.game.NP_PlayerData;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class GamePlayer {

	private PlayerAttributes attributes = new PlayerAttributes();
	private RespawnTimer respawnTimer;
	private PlayerRespawnListener respawnListener;
	private PlayerMovement playerMovement;
	private PlayerInput playerInput = new PlayerInput();
	private Jetpack jetpack =new Jetpack();
	

	public GamePlayer(String playerName, int playerID) {
		attributes.setPlayerID(playerID);
		attributes.setPlayerName(playerName);
		playerMovement = new PlayerMovement(playerInput, this);
	}

	public void processInput(NP_PlayerUpdate update) {
		playerInput.process(update);
	}

	public void update(float delta) {
		playerMovement.update(delta);
	}

	public void startRespawntimer(final SpawnPoint spawnpoint, int time) {
		respawnTimer.startRespawnTimer(time, new RespawnListener() {
			@Override
			public void doRespawn() {
				respawnPlayer(spawnpoint.getX(), spawnpoint.getY());
			}
		});
	}

	private void respawnPlayer(float x, float y) {
		playerMovement.spawnAtPosition(x, y);
		respawnListener.playerRespawned(this);
		attributes.getHealth().revive();
	}

	public void setRespawnListener(PlayerRespawnListener respawnListener) {
		this.respawnListener = respawnListener;
	}

	public PlayerAttributes getAttributes() {
		return attributes;
	}
	
	public Jetpack getJetpack() {
		return jetpack;
	}

	public NP_PlayerData getData() {
		NP_PlayerData data=new NP_PlayerData();
		Location location = playerMovement.getLocation();
		
		data.xpos=location.getXpos();
		data.ypos=location.getYpos();
		return data;
	}
}
