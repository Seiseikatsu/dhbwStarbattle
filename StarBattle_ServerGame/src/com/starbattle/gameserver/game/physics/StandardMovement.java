package com.starbattle.gameserver.game.physics;

import com.starbattle.gameserver.game.input.MovementControl;
import com.starbattle.gameserver.game.input.PlayerInput;

public class StandardMovement extends MovementType {

	private float movementSpeed = 0.1f;
	private float jumpSpeed = 7f;

	private static float minimumJumpTime = 0.2f;
	private int maximumJumpsInAir = 1;
	private int usedJumps;

	public StandardMovement(ObjectMovement movement) {
		super(movement);
	}

	public void setMaximumJumpsInAir(int maximumJumpsInAir) {
		this.maximumJumpsInAir = maximumJumpsInAir;
	}

	@Override
	public void updateMovement(PlayerInput playerInput) {

		MovementControl control = playerInput.getMovementControl();

		// move left / right
		if (control.isMoveLeft()) {
			movement.horizontalMovement(-movementSpeed);
		}
		if (control.isMoveRight()) {
			movement.horizontalMovement(movementSpeed);
		}

		// ONLY FOR DEBUG FREE MOVEMENT
		/*
		 * if (control.isMoveUp()) { movement.verticalMovement(-movementSpeed);
		 * } if (control.isMoveDown()) {
		 * movement.verticalMovement(movementSpeed); }
		 */

		// jump
		if (control.isMoveUp()) {

			ObjectGravity gravity = movement.getGravity();
			if (gravity.isInAir()) {
				// wenn spieler in der luft und nochmal springen will =>

				// wiederholtes srpingen mit springtaste erst ab bestimmter
				// flugzeit erlauben
				if (gravity.getAirTime() > minimumJumpTime) {
					if (usedJumps >= maximumJumpsInAir) {
						// nicht erlauben nochmal zu springen
						return;
					} else {
						// neuer sprung
						movement.getGravity().cancelMovement();
						usedJumps++;
					}
				}
			} else {
				// reset jumps
				usedJumps = 0;
			}
			movement.getGravity().jump(jumpSpeed);
		}
	}

	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
}
