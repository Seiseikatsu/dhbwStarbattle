package com.starbattle.ingame.game;

import com.starbattle.ingame.game.input.ActionSet;
import com.starbattle.ingame.game.input.MouseCursor;
import com.starbattle.ingame.game.input.PlayerInput;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.network.SendUpdateListener;
import com.starbattle.network.connection.objects.constant.NP_Constants;
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
		
		
		PlayerObject myself = game.getPlayers().getMyPlayer();
		update.weapon_angle=myself.getWeaponAngle();
		
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
		
		//mouse check
		MouseCursor mouse = playerInput.getMouseCursor();
		if(mouse.isLeftClick())
		{
		update.action=NP_Constants.FIRE_WEAPON;
		}
		
		//reset key and mouse infos
		mouse.resetPressedButtons();
		actions.reset();
	}

}