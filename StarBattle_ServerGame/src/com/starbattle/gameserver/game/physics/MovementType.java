package com.starbattle.gameserver.game.physics;

import com.starbattle.gameserver.game.input.PlayerInput;

public abstract class MovementType {

	protected ObjectMovement movement;
	
	public MovementType(ObjectMovement movement) {
		this.movement=movement;
	}
	
	public abstract void updateMovement(PlayerInput playerInput);
}
