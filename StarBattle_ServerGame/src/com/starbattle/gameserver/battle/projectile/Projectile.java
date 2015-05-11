package com.starbattle.gameserver.battle.projectile;

import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.player.GamePlayer;

public abstract class Projectile {

	protected Shape collisonShape;
	protected Location location;
	protected GamePlayer source;
	protected float fireAngle;
	protected EffectTrigger effectTrigger;
	protected int id;
	
	
	public Projectile(GamePlayer source, Location location, float fireAngle, EffectTrigger effectTrigger) {
		this.source=source;
		this.fireAngle=fireAngle;
		this.location=location.copy();
		this.effectTrigger=effectTrigger;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public abstract void update(float delta);
	
	public abstract Damage onHit(GamePlayer target);
	
	public Location getLocation() {
		return location;
	}
	
	public Shape getCollisonShape() {
		return collisonShape;
	}

	public GamePlayer getSource() {
		return source;
	}
	
	public float getFireAngle() {
		return fireAngle;
	}
}
