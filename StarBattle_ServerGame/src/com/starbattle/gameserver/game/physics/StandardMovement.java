package com.starbattle.gameserver.game.physics;

import com.starbattle.gameserver.game.input.MovementControl;
import com.starbattle.gameserver.game.input.PlayerInput;

public class StandardMovement extends MovementType {

	private float movementSpeed = 3f;
	private float jumpSpeed = 10f;

	public StandardMovement(ObjectMovement movement) {
		super(movement);
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
		
		// jump
		if (control.isMoveUp()) {
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
