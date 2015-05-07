package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.EffectTriggerFactory;
import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.game.physics.ObjectMovement;
import com.starbattle.gameserver.game.physics.StandardMovement;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.network.connection.objects.game.NP_PlayerData;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;
import com.starbattle.network.connection.objects.constant.TriggerEffects;

public class PlayerMovement {

	private ObjectMovement objectMovement;
	private StandardMovement standardMovement;
	private PlayerInput playerInput;
	private GamePlayer gamePlayer;
	private EffectTrigger effectTrigger;

	public PlayerMovement(PlayerInput playerInput, GamePlayer player, CollisionDetection collisionDetection,
			EffectTrigger effectTrigger) {
		objectMovement = new ObjectMovement(collisionDetection);
		standardMovement = new StandardMovement(objectMovement);
		this.playerInput = playerInput;
		this.gamePlayer = player;
		this.effectTrigger = effectTrigger;
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

	public void spawnAtPosition(Location l) {
		objectMovement.teleport(l);
		objectMovement.getGravity().cancelMovement();
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

		// trigger jump effect animation when jumping in air
		
		
		if (objectMovement.objectJumped()&&standardMovement.getUsedJumps()>1) {
			NP_TriggerEffect effect = EffectTriggerFactory.createEffect(pos, TriggerEffects.JUMP_ANIMATION, 0,gamePlayer);
			effectTrigger.triggerEffect(effect);
		}

		objectMovement.resetMovementInfo();
	}
	
	public Location getLocation()
	{
		return objectMovement.getLocation();
	}

}
