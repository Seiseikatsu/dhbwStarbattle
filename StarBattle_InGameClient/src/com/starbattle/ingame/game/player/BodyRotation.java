package com.starbattle.ingame.game.player;

public enum BodyRotation {

	LEFT_LEG_ANGLE(0), LEFT_ARM_ANGLE(1), RIGHT_FOOT_ANGLE(2), RIGHT_ARM_ANGLE(3), BODY_ANGLE(5);

	private int rotationArrayIndex;

	private BodyRotation(int i) {
		rotationArrayIndex = i;
	}

	public int getRotationArrayIndex() {
		return rotationArrayIndex;
	}
}
