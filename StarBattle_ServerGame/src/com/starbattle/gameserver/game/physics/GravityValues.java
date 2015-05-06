package com.starbattle.gameserver.game.physics;

public enum GravityValues {

	ERDE(9.79f), MERKUR(3.61f), VENUS(8.83f), MARS(3.75f), JUPITER(26.0f), SATURN(11.2f), URANUS(10.5f), NEPTUN(13.3f), PLUTO(
			0.61f);

	private float gravity;

	private GravityValues(float gravity) {
		this.gravity = gravity;
	}

	public float getGravity() {
		return gravity;
	}
}
