package com.starbattle.gameserver.game.input;

import com.starbattle.network.connection.objects.constant.NP_Constants;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class PlayerInput {

	private MovementControl movementControl=new MovementControl();
	private boolean fireWeapon;

	public PlayerInput() {

	}
	
	

	/**
	 * Processes the player input into move and action commands
	 * 
	 * @param update
	 */
	public void process(NP_PlayerUpdate update) {
		movementControl.processInput(update);

		int action = update.action;
		switch (action) {
		case NP_Constants.NO_ACTION:
			break;
		case NP_Constants.FIRE_WEAPON:
			fireWeapon = true;
			break;
		}

	}

	/**
	 * Only call after every other game logic update!
	 * 
	 * @param delta
	 */
	public void updateReset(float delta) {
		movementControl.update(delta);
		fireWeapon = false;
	}

	public boolean isFireWeapon() {
		return fireWeapon;
	}
	
	public MovementControl getMovementControl() {
		return movementControl;
	}
}
