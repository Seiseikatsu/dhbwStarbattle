package com.starbattle.gameserver.player;

public class Jetpack {

	private final static float FRAME_FUEL_CONSUMPTION = 0.1f;
	private final static float FRAME_FUEL_REFILL = 0.01f;
	private final static float MAX_TANK = 200;
	private float fuel, tank;
	private boolean isUsingJetpack = false;
	private boolean autoRefill = true;

	public Jetpack() {
		tank = MAX_TANK;
	}

	public void useJetpack() {
		if (canUseJetpack()) {
			isUsingJetpack = true;
		}
	}

	public void setAutoRefill(boolean autoRefill) {
		this.autoRefill = autoRefill;
	}

	public void setTankSize(float tank) {
		this.tank = tank;
	}

	public void update(float delta) {

		if (isUsingJetpack) {
			fuel -= FRAME_FUEL_CONSUMPTION * delta;
			if (fuel < 0) {
				fuel = 0;
			}
		} else {
			// slowly refill
			if (autoRefill) {
				fuel += FRAME_FUEL_REFILL * delta;
				if (fuel > tank) {
					fuel = tank;
				}
			}
		}
		isUsingJetpack = false;
	}

	public boolean canUseJetpack() {
		return fuel > 0;
	}

	public boolean isUsingJetpack() {
		return isUsingJetpack;
	}

	public float getFuel() {
		return fuel;
	}

	public float getTank() {
		return tank;
	}
}
