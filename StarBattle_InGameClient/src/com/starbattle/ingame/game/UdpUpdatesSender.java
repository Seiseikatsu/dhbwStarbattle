package com.starbattle.ingame.game;

import com.starbattle.ingame.game.input.ActionSet;
import com.starbattle.ingame.game.input.PlayerInput;
import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.network.SendUpdateListener;
import com.starbattle.network.connection.objects.NP_Constants;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class UdpUpdatesSender implements SendUpdateListener {

	private GameCore game;
	private GameNetwork network;
	private PlayerInput playerInput;
	
	public UdpUpdatesSender(GameManager manager) {
		this.game = manager.getGameCore();
		this.network = manager.getNetwork();
		this.playerInput = manager.getPlayerInput();
	}

	@Override
	public void sendUdpUpdates() {

		NP_PlayerUpdate update = new NP_PlayerUpdate();
		// process updates

		processInputs(update);

		// send to server
		network.sendUDP(update);
	}

	private void processInputs(NP_PlayerUpdate update) {
		ActionSet actions = playerInput.getActionSet();
		boolean moveLeft = actions.isMove_left();
		boolean moveUp = actions.isMove_up();
		boolean moveDown = actions.isMove_down();
		boolean moveRight = actions.isMove_right();

		if (moveLeft) {
			update.horizontalMovement = NP_Constants.FORWARD_MOVEMENT;
		} else if (moveRight) {
			update.horizontalMovement = NP_Constants.BACKWARD_MOVEMENT;
		}

		if (moveUp) {
			update.verticalMovement = NP_Constants.FORWARD_MOVEMENT;
		} else if (moveDown) {
			update.verticalMovement = NP_Constants.BACKWARD_MOVEMENT;
		}

		actions.reset();
	}

}