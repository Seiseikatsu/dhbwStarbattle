package com.starbattle.gameserver.game.input;


import com.starbattle.network.connection.objects.constant.NP_Constants;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class MovementControl {

	/**
	 * Move Player Left
	 */
	private boolean moveLeft;
	/**
	 * Move Player Right
	 */
	private boolean moveRight;
	/**
	 * Move Up ( Jump or move up in fly mode)
	 */
	private boolean moveUp;
	/**
	 * Move Down (Fall faster or move trough special blocks???)
	 */
	private boolean moveDown;

	public MovementControl() {

	}

	public void processInput(NP_PlayerUpdate input) {
		int vertical = input.verticalMovement;
		int horizontal = input.horizontalMovement;
		int action = input.action;

		moveLeft = false;
		moveRight = false;
		moveUp = false;
		moveDown = false;

		switch (horizontal) {
		case NP_Constants.FORWARD_MOVEMENT:
			moveLeft = true;
			break;
		case NP_Constants.BACKWARD_MOVEMENT:
			moveRight = true;
			break;
		}

		switch (vertical) {
		case NP_Constants.FORWARD_MOVEMENT:
			moveUp = true;
			break;
		case NP_Constants.BACKWARD_MOVEMENT:
			moveDown = true;
			break;
		}

	}

	/**
	 * NOTE: Uodate only after game logic, so no commands will be ignored!
	 * 
	 * @param delta
	 */
	public void update(float delta) {

		// reset old commands

	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public boolean isMoveUp() {
		return moveUp;
	}

	public boolean isMoveDown() {
		return moveDown;
	}

	
}
