package com.starbattle.gameserver.player;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.EffectTriggerFactory;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.map.MapBorder;
import com.starbattle.gameserver.map.ServerMap;
import com.starbattle.gameserver.map.SpawnPoint;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.weapon.WeaponInventar;
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
	private WeaponInventar weapons;
	private Shape collisionShape;
	private boolean connected=true;

	public GamePlayer(String playerName, int playerID, GameControl control) {
		this.effectTrigger = control.getEffectTrigger();
		attributes.setPlayerID(playerID);
		attributes.setPlayerName(playerName);
		playerMovement = new PlayerMovement(playerInput, this, control.getCollisionDetection(), effectTrigger);
		weapons = new WeaponInventar(this, control);

		collisionShape=new Ellipse(0,0,0.18f,0.55f);
		
		attributes.getHealth().setHealthListener(new HealthListener() {
			@Override
			public void playerKilled() {
				//reset weapons ammo
				weapons.resetOnDeath();
				// trigger death animation
				NP_TriggerEffect effect = EffectTriggerFactory.createEffect(getLocation(),
						TriggerEffects.DEATH_ANIMATION, 0, GamePlayer.this);
				effectTrigger.triggerEffect(effect);
			}
		});
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public boolean isConnected() {
		return connected;
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
				weapons.fireWeapon();
				break;
			case NP_Constants.SWITCH_WEAPON_FORWARDS:
				weapons.switchWeapon(true);
				break;
			case NP_Constants.SWITCH_WEAPON_BACKWARDS:
				weapons.switchWeapon(false);
				break;
			}
		}
	}

	public void update(float delta) {
		weapons.update(delta);
		if (isAlive()) {
			playerMovement.update(delta);
			Location l=getLocation();
			collisionShape.setCenterX(l.getXpos());
			collisionShape.setCenterY(l.getYpos());
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
		data.health = attributes.getHealth().getHealthPercent();
		data.alive = isAlive();
		data.points=attributes.getPoints();
		data.weapon_id=weapons.getSelectedWeapon();
		data.ammo=weapons.getCurrentAmmo();
		if (respawnTimer.isRunning()) {
			data.respawnTime = (byte) respawnTimer.getRespawnTime();
		}
		return data;
	}

	public Location getLocation() {
		return playerMovement.getLocation();
	}

	public boolean isAlive() {
		return !attributes.getHealth().isDead();
	}

	public Shape getCollisionShape() {
		return collisionShape;
	}

	public WeaponInventar getWeapons() {
		return weapons;
	}
	
	public PlayerMovement getPlayerMovement() {
		return playerMovement;
	}
}
