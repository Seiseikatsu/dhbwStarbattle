package com.starbattle.ingame.game.player;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class PlayerContainer {

	private List<PlayerObject> players = new ArrayList<PlayerObject>();
	private int myID;

	public PlayerContainer() {

	}

	public void init(NP_PrepareGame init) {
		int playerSize = init.playerNames.length;
		for (int i = 0; i < playerSize; i++) {
			String name = init.playerNames[i];
			int team = init.teams[i];
			PlayerObject player = new PlayerObject(name, team);
			players.add(player);
		}
		myID = init.playerID;
	}

	public int getMyID() {
		return myID;
	}

	public int getNumberOfPlayers() {
		return players.size();
	}

	public PlayerObject getPlayer(int id) {
		return players.get(id);
	}

	public PlayerObject getMyPlayer() {
		return players.get(myID);
	}

	public void update(float delta) {
		for (PlayerObject player : players) {
			player.update(delta);
		}
	}

}
