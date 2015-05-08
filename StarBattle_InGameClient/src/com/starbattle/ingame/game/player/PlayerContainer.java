package com.starbattle.ingame.game.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.network.connection.objects.game.NP_PlayerData;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class PlayerContainer {

	private List<PlayerObject> players = new ArrayList<PlayerObject>();
	private List<PlayerPoints> points = new ArrayList<PlayerPoints>();
	private PlayerPointsComparator playerPointsComparator = new PlayerPointsComparator();
	private int myID;

	public PlayerContainer() {

	}

	public void init(NP_PrepareGame init) {

		int playerSize = init.playerNames.length;
		for (int i = 0; i < playerSize; i++) {
			String name = init.playerNames[i];
			int team = init.teams[i];
			PlayerObject player = new PlayerObject(name, team);
			System.out.println("> Init Player \"" + name + "\" for Team " + team);
			players.add(player);
			points.add(player.getPoints());
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

	public void update(NP_PlayerData[] playerData) {

		for (int i = 0; i < playerData.length; i++) {
			PlayerObject player = players.get(i);
			player.update(playerData[i]);

		}

		// sort and update points placement
		Collections.sort(points, playerPointsComparator);
		for (int i = 0; i < 3; i++) {
			// set place: 2= first , 1=second, 0= third, kleiner 0 nix
			if (i < points.size()) {
				points.get(i).setPlace(2 - i);
			}
		}
	}

	private class PlayerPointsComparator implements Comparator<PlayerPoints> {

		@Override
		public int compare(PlayerPoints o1, PlayerPoints o2) {
			int p1 = o1.getPoints();
			int p2 = o2.getPoints();
			if (p1 > p2) {
				return -1;
			} else if (p2 < p1) {
				return 1;
			}
			return 0;
		}

	}

}
