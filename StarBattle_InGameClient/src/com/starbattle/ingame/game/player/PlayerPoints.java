package com.starbattle.ingame.game.player;

public class PlayerPoints {

	private int points;
	private int place;

	public PlayerPoints() {

	}

	public void setPlace(int place) {

		this.place = place;
		if (place < -1) {
			place = -1;
		}
	}

	public int getPlace() {
		return place;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

}
