package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.EffectTriggerFactory;
import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.map.MapBorder;
import com.starbattle.gameserver.map.ServerMap;
import com.starbattle.gameserver.map.SpawnPoint;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.network.connection.objects.constant.NP_Constants;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_PlayerData;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class GamePlayer {

	private PlayerAttributes attributes = new PlayerAttributes();
	private RespawnTimer respawnTimer = new RespawnTimer();
	private PlayerRespawnListener respawnListener;
	private PlayerMovement playerMovement;
	private PlayerInput playerInput = new PlayerInput();
	private Jetpack jetpack = new Jetpack();
	private EffectTrigger effectTrigger;
	private PlayerWeaponHandler weaponHandler;

	public GamePlayer(String playerName, int playerID, GameControl control) {
		this.effectTrigger = control.getEffectTrigger();
		attributes.setPlayerID(playerID);
		attributes.setPlayerName(playerName);
		playerMovement = new PlayerMovement(playerInput, this, control.getCollisionDetection(), effectTrigger);
		weaponHandler = new PlayerWeaponHandler(this, effectTrigger);

		attributes.getHealth().setHealthListener(new HealthListener() {
			@Override
			public void playerKilled() {
				// trigger death animation
				NP_TriggerEffect effect = EffectTriggerFactory.createEffect(getLocation(),
						TriggerEffects.DEATH_ANIMATION, 0, GamePlayer.this);
				effectTrigger.triggerEffect(effect);
			}
		});
	}

	public void processInput(NP_PlayerUpdate update) {
		playerInput.process(update);
		// update weapon angle
		attributes.setWeaponAngle(update.weapon_angle);
		int action = update.action;

		if (isAlive()) {
			switch (action) {
			case NP_Constants.NO_ACTION:
				// do nothing
				break;
			case NP_Constants.FIRE_WEAPON:
				// try to fire weapon
				weaponHandler.fireWeapon();
				break;
			}
		}
	}

	public void update(float delta) {
		if (isAlive()) {
			playerMovement.update(delta);
		}
	}

	public void startRespawntimer(final SpawnPoint spawnpoint, int time) {
		respawnTimer.startRespawnTimer(time, new RespawnListener() {
			@Override
			public void doRespawn() {

				respawnPlayer(spawnpoint.getLocation());
			}
		});
	}

	public void teleportTo(Location l) {
		playerMovement.teleport(l);
	}

	private void respawnPlayer(Location l) {
		playerMovement.spawnAtPosition(l);
		respawnListener.playerRespawned(this);
		attributes.getHealth().revive();
	}

	public void setRespawnListener(PlayerRespawnListener respawnListener) {
		this.respawnListener = respawnListener;
	}

	public PlayerAttributes getAttributes() {
		return attributes;
	}

	public Jetpack getJetpack() {
		return jetpack;
	}

	public NP_PlayerData getData() {
		NP_PlayerData data = new NP_PlayerData();
		playerMovement.writeMovementData(data);
		data.visible = isAlive();
		return data;
	}

	public Location getLocation() {
		return playerMovement.getLocation();
	}

	public boolean isAlive() {
		return !attributes.getHealth().isDead();
	}

}
