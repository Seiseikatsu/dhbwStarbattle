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
		movement.setMovementListener(new MovementListener() {

			@Override
			public void landedFromJumping() {
				// reset jumps
				usedJumps = 0;
			}
		});
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

			
			if (usedJumps == 0) {

				if (!gravity.isInAir()) {
					// first jump from ground
					movement.getGravity().jump(jumpSpeed);
					usedJumps++;
				} else {
					if (maximumJumpsInAir > 0) {
						//first  jump while falling 
						movement.getGravity().cancelMovement();
						movement.getGravity().jump(jumpSpeed);
						usedJumps += 2; //no additional jump from ground, only air jumps
					}

				}
			} else {
				// jump while mid air 
				if (gravity.isInAir()) {
					if (usedJumps <= maximumJumpsInAir) {

						// mindest dealy bis nächster sprung gemacht werden kann
						if (gravity.getAirTime() > minimumJumpTime) {
							movement.getGravity().cancelMovement();
							movement.getGravity().jump(jumpSpeed);
							usedJumps++;
						}

					}
				}
			}

		}
	}

	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getUsedJumps() {
		return usedJumps;
	}
}
