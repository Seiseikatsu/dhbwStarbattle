package com.starbattle.gameserver.weapon;

import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.player.GamePlayer;

public abstract class Weapon {

	protected ProjectileEmitter emitter;
	protected GamePlayer owner;
	protected GameControl game;
	protected int ammo, maxAmmo;
	protected float fireDelay;
	private float currentFireDelay;
	private boolean autoReload = false;
	private float autoReloadTime, currentAutoReloadTime;
	protected EffectTrigger effectTrigger;

	public Weapon(GamePlayer player, GameControl gameControl, ProjectileEmitter emitter) {
		this.emitter = emitter;
		this.owner = player;
		this.game = gameControl;
		this.effectTrigger=game.getEffectTrigger();
	}

	protected void init(int ammo, float fireDelay) {
		this.ammo = ammo;
		this.fireDelay = fireDelay;
	}

	protected void setAutoReload(float autoReloadTime) {
		this.autoReloadTime = autoReloadTime;
		this.autoReload = true;
	}

	protected abstract void onFiring(Location sourceLocation,float fireAngle);

	public void update(float delta) {
		if (currentFireDelay > 0) {
			currentFireDelay -= delta;
		}
		if (autoReload) {
			currentAutoReloadTime += delta;
			if (currentAutoReloadTime >= autoReloadTime) {
				currentAutoReloadTime = 0;
				reload(1);
			}
		}
	}

	public void reloadFull() {
		ammo = maxAmmo;
	}

	public void reload(int ammo) {
		this.ammo += ammo;
		if (ammo > maxAmmo) {
			ammo = maxAmmo;
		}
	}

	public void dropAmmo() {
		ammo = 0;
	}
	
	public boolean hasAmmo()
	{
		return ammo>0;
	}

	public void fire() {
		currentFireDelay = fireDelay;
		ammo--;
		//TODO maybe calc correct weapon fire location, not just center of player
		// => hard to do, becasue client only knows how to render and calc hand + weapon pos
		onFiring(owner.getLocation(),owner.getAttributes().getWeaponAngle());
	}

	public boolean canFire() {
		if (ammo > 0 && currentFireDelay <= 0) {
			return true;
		}
		return false;
	}

}
