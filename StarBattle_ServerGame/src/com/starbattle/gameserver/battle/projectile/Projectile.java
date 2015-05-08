package com.starbattle.gameserver.battle.projectile;

import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.player.GamePlayer;

public abstract class Projectile {

	protected Shape collisonShape;
	protected Location location;
	protected GamePlayer source;
	protected float fireAngle;
	
	
	public Projectile(GamePlayer source, Location location, float fireAngle) {
		this.source=source;
		this.fireAngle=fireAngle;
		this.location=location;
	}
	
	public abstract void update(float delta);
	
	public abstract Damage calcDamage(GamePlayer target);
	
	public Location getLocation() {
		return location;
	}
	
	public Shape getCollisonShape() {
		return collisonShape;
	}
	
}
