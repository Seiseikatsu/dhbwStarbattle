package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.gameserver.game.physics.ObjectMovement;
import com.starbattle.gameserver.game.physics.StandardMovement;

public class PlayerMovement {

	private ObjectMovement objectMovement;
	private StandardMovement standardMovement;
	private PlayerInput playerInput;
	private GamePlayer gamePlayer;

	public PlayerMovement(PlayerInput playerInput, GamePlayer player) {
		objectMovement = new ObjectMovement();
		standardMovement = new StandardMovement(objectMovement);
		this.playerInput = playerInput;
		this.gamePlayer = player;
	}

	public void update(float delta) {
		if (gamePlayer.getJetpack().isUsingJetpack()) {
			// TODO: use jetpack movement

		} else {
			// standard movement for walking and jumping
			standardMovement.updateMovement(playerInput);
		}
		objectMovement.update(delta);
		playerInput.updateReset(delta);
	}

	public void spawnAtPosition(float x, float y) {

	}
}
