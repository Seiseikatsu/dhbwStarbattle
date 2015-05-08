package com.starbattle.ingame.game.player;

import com.starbattle.ingame.resource.PlayerGraphics;

public class PlayerDisplay {

	private float[] rotation;
	private float legRotCount = (float) Math.PI;

	private boolean weaponIsFiring = false;
	private PlayerGraphics graphic;
	private boolean lookingLeft = false;
	private boolean isVisible = true;
	
	public PlayerDisplay() {

		graphic = PlayerGraphics.ASTRONAUT;
		rotation = new float[BodyRotation.values().length];

	}
	

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isVisible() {
		return isVisible;
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

	float resetGunFired = 0;

	public void updateBodyAnimation(float delta, float xspeed, float yspeed) {

		resetGunFired += delta;
		if (resetGunFired > 10) {
			resetGunFired = 0;
			weaponIsFiring = false;
		}

		// update legs on running
		int r = 70;
		xspeed *= 2;
		if (yspeed != 0) {
			xspeed /= 2;
			r = 80;
			// legRotCount = (float) Math.PI/2;
		}

		if (yspeed < 0) {

			// wenn hochspringt
			rotation[4] += xspeed * 3.5;
		} else if (yspeed > 0) {
			float rr = rotation[4];
			float dd = 1f;
			if (rr > 0) {
				rotation[4] -= dd;
			} else if (rr < 0) {
				rotation[4] += dd;
			}
		} else {
			rotation[4] = 0;
		}

		int i = BodyRotation.RIGHT_FOOT_ANGLE.getRotationArrayIndex();
		legRotCount += Math.abs(xspeed);

		if (xspeed == 0) {
			// normalize leg

			float norm = (float) (Math.abs(legRotCount) % Math.PI);

			float f = 0.2f;

			float dist = (float) (Math.abs(norm) - Math.PI / 2);

			if (dist > f / 2) {

				if (norm > Math.PI / 2) {
					legRotCount += f;

				} else {
					legRotCount -= f;
				}

			} else {

				legRotCount = 0;
			}

		}
		rotation[i] = (float) (Math.sin(legRotCount) * r);
		rotation[i - 2] = -rotation[i];

		rotation[1] = -rotation[i] / 2;
		rotation[3] = -rotation[i - 2] / 2;

		// rotation[BodyRotation.LEFT_LEG_ANGLE.getRotationArrayIndex()]=-leg;

		// rotation[BodyRotation.RIGHT_FOOT_ANGLE.getRotationArrayIndex()]+=xspeed*50;
		// rotation[BodyRotation.LEFT_LEG_ANGLE.getRotationArrayIndex()]-=xspeed*50;

	}

	public boolean isWeaponIsFiring() {
		return weaponIsFiring;
	}

	public void firedWeapon() {
		resetGunFired = 0;
		weaponIsFiring = true;
	}
}
