package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.game.physics.ObjectMovement;
import com.starbattle.gameserver.game.physics.StandardMovement;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.network.connection.objects.game.NP_PlayerData;

public class PlayerMovement {

	private ObjectMovement objectMovement;
	private StandardMovement standardMovement;
	private PlayerInput playerInput;
	private GamePlayer gamePlayer;

	public PlayerMovement(PlayerInput playerInput, GamePlayer player, CollisionDetection collisionDetection) {
		objectMovement = new ObjectMovement(collisionDetection);
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
		// objectMovement.getLocation().moveY(objectMovement.getLocation().getYpos()+0.3f);
		objectMovement.update(delta);
		playerInput.updateReset(delta);
	}

	public void spawnAtPosition(float x, float y) {

	}

	public void teleport(Location l) {

		objectMovement.teleport(l);
	}

	public void writeMovementData(NP_PlayerData data) {

		
		data.facingLeft = objectMovement.isFacingLeft();
		Location pos = objectMovement.getLocation();
		data.xpos = pos.getXpos();
		data.ypos = pos.getYpos();
		data.xspeed = objectMovement.getMovementX();
		data.yspeed = objectMovement.getMovementY();
		
		objectMovement.resetMovementInfo();
	}

}
