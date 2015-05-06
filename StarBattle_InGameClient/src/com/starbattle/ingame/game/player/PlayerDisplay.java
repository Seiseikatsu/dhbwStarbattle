package com.starbattle.ingame.game.player;

import com.starbattle.ingame.resource.PlayerGraphics;

public class PlayerDisplay {

	private float[] rotation;
	private float legRotCount = (float) Math.PI;

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

	public void updateBodyAnimation(float delta, float xspeed, float yspeed) {

		// update legs on running
		if(yspeed!=0)
		{
		xspeed/=2;	
		}
		
		int i = BodyRotation.RIGHT_FOOT_ANGLE.getRotationArrayIndex();
		legRotCount += Math.abs(xspeed);

		
		
		if (xspeed == 0) {
			// normalize leg

			float norm = (float) (Math.abs(legRotCount) % Math.PI);

			float f = 0.2f;

			float dist = (float) (Math.abs(norm) - Math.PI / 2);
	
			if (dist > f/2) {

				if (norm > Math.PI / 2) {
					legRotCount += f;

				} else {
					legRotCount -= f;
				}

			}
			else{
				
				legRotCount=0;
			}

		}

		
		
		rotation[i] = (float) (Math.sin(legRotCount) * 70);
		rotation[i - 2] = -rotation[i];
		
		rotation[1] = -rotation[i]/2;
		rotation[3] = -rotation[i-2]/2;
		// rotation[BodyRotation.LEFT_LEG_ANGLE.getRotationArrayIndex()]=-leg;

		// rotation[BodyRotation.RIGHT_FOOT_ANGLE.getRotationArrayIndex()]+=xspeed*50;
		// rotation[BodyRotation.LEFT_LEG_ANGLE.getRotationArrayIndex()]-=xspeed*50;

	}

}
