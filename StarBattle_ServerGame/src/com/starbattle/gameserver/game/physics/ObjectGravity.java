package com.starbattle.gameserver.game.physics;

public class ObjectGravity {

	/**
	 * Ver�ndert nicht die Sprungh�he / Fallgeschwindigkeit, sondern nur �ber
	 * welchen Zeitrum sich diese strecken.
	 * 
	 * Bei niedrigen Werten geschieht die Bewgung langsamer, bei h�heren
	 * schneller.
	 * 
	 * Der "echte" Wert m�sste man wahrscheinlich abh�ngig von der
	 * Spielgeschwindigkeit ausrechnen. Einfacher ist es einen "guten"
	 * Abzusch�tzen, der gut zum Spielgeschehen und der Spielgeschwindigkeit
	 * passt.
	 * 
	 * 
	 */
	private final static float TIME_UNITS = 0.025f;

	private float g_factor;
	private float time;
	private float jumpSpeed;
	private float lastValue;
	private boolean inAir = false;
	private ObjectMovement objectMovement;
	private boolean objectJumped=false;

	public ObjectGravity(ObjectMovement objectMovement) {
		setG_factor(GravityValues.ERDE);
		this.objectMovement = objectMovement;
	}

	public void setG_factor(GravityValues gravity) {
		this.g_factor = gravity.getGravity();
	}

	public void update(float delta) {
		// System.out.println("kkkk");
		if (inAir) {
			// update time
			float deltaTime = TIME_UNITS * delta;
			time += deltaTime;
			// update y (vertikaler wurf formel)
			float jumpY = (float) (jumpSpeed * time - (g_factor / 2) * Math.pow(time, 2));
			float yDelta = jumpY - lastValue;
			lastValue = jumpY;
			// update location

			//maximale falldistanz pro schritt nicht gr��er als die feldgr��e der map!
			//ansonsten k�nnte man bei gro�en st�rzen durch bl�cke fallen und unendlich beschleunigt werden :(
			if (Math.abs(yDelta) >= 1) {
				float maxValue = 0.99f;
				if (yDelta < 0) {
					yDelta = -maxValue;
				} else {
					yDelta = maxValue;
				}
			}

			objectMovement.verticalMovement(-yDelta);
		}
	}

	public void jump(float speed) {
		if (!inAir) {
			jumpSpeed = speed;
			objectJumped=true;
			startGravity();
		}
	}
	
	public boolean isObjectJumped() {
		boolean b=objectJumped;
		objectJumped=false;
		return b;
	}

	public void startFalling() {
		if (!inAir) {
			jumpSpeed = 0;
			startGravity();
		}
	}

	public void cancelMovement() {
		inAir = false;
	}

	private void startGravity() {
		inAir = true;
		time = 0;
		lastValue = 0;
	}

	public boolean isInAir() {
		return inAir;
	}

	public float getAirTime() {
		return time;
	}
}
