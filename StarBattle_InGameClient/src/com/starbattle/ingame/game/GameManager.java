package com.starbattle.ingame.game;

import org.newdawn.slick.Input;

import com.starbattle.ingame.game.input.PlayerInput;
import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.network.SendUpdateListener;
import com.starbattle.ingame.network.SendUpdateTimer;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.client.SendServerListener;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameManager {

	private GameCore gameCore;
	private GameNetwork network;
	private ResourceContainer resourceContainer;
	private SendUpdateTimer sendUpdateTimer;
	private String mapName;
	private PlayerInput playerInput;
	
	public GameManager(GameNetwork network) {
		this.network = network;
		resourceContainer = new ResourceContainer();
		gameCore = new GameCore(resourceContainer);
	}

	public void startingGame() {
		// start send udp update timer
		SendUpdateListener sendUpdateListener = new UdpUpdatesSender(this);
		sendUpdateTimer = new SendUpdateTimer(sendUpdateListener);
		sendUpdateTimer.start();
	}

	public GameCore getGameCore() {
		return gameCore;
	}

	public GameNetwork getNetwork() {
		return network;
	}

	public ResourceContainer getResourceContainer() {
		return resourceContainer;
	}

	public String getMapName() {
		return mapName;
	}

	public void initGame(NP_PrepareGame prepareGame) {
		this.mapName = prepareGame.mapName;
		System.out.println("Init Game:");
		System.out.println("> Map Name: " + mapName);
		gameCore.initPlayers(prepareGame);
	}

	public void closeGame() {
		sendUpdateTimer.close();
	}

	public void prepareInput(Input input) {
		playerInput=new PlayerInput(input);
		playerInput.init();
	}

	public PlayerInput getPlayerInput() {
		return playerInput;
	}
	
}
