package com.starbattle.ingame.game.player;

import com.starbattle.ingame.resource.PlayerGraphics;

public class PlayerDisplay {

	private float[] rotation;
	private PlayerGraphics graphic;
	private boolean lookingLeft = false;

	public PlayerDisplay() {

		graphic = PlayerGraphics.ASTRONAUT;
		rotation = new float[BodyRotation.values().length];
	}

	public void setLookingLeft(boolean lookingLeft) {
		this.lookingLeft = lookingLeft;
	}

	public boolean isLookingLeft() {
		return lookingLeft;
	}

	public PlayerGraphics getGraphic() {
		return graphic;
	}

	public float[] getRotation() {
		return rotation;
	}

}
