package com.starbattle.gameserver.object;

import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.map.MapBorder;
import com.starbattle.gameserver.map.collision.CollisionDetection;

public class GameControl {

	private CollisionDetection collisionDetection;
	private MapBorder mapBorder;
	private EffectTrigger effectTrigger;
	private ProjectileEmitter projectileEmitter;
	
	public GameControl() {
	}
	
	public void setProjectileEmitter(ProjectileEmitter projectileEmitter) {
		this.projectileEmitter = projectileEmitter;
	}
	
	public ProjectileEmitter getProjectileEmitter() {
		return projectileEmitter;
	}
	
	public void setCollisionDetection(CollisionDetection collisionDetection) {
		this.collisionDetection = collisionDetection;
	}
	
	public void setEffectTrigger(EffectTrigger effectTrigger) {
		this.effectTrigger = effectTrigger;
	}
	
	public void setMapBorder(MapBorder mapBorder) {
		this.mapBorder = mapBorder;
	}
	
	public CollisionDetection getCollisionDetection() {
		return collisionDetection;
	}
	
	public EffectTrigger getEffectTrigger() {
		return effectTrigger;
	}
	
	public MapBorder getMapBorder() {
		return mapBorder;
	}
	
}
