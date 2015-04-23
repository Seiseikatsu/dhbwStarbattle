package com.starbattle.ingame.game;

import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameManager {

	private GameCore gameCore;
	private GameNetwork network;
	private ResourceContainer resourceContainer;
	private String mapName;

	public GameManager(GameNetwork network) {
		this.network = network;
		resourceContainer = new ResourceContainer();
		gameCore = new GameCore(resourceContainer);
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
		System.out.println("> Map Name: "+mapName);
		gameCore.initPlayers(prepareGame);
	}


}
