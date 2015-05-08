package com.starbattle.gameserver.battle.projectile.impl;

import com.starbattle.gameserver.battle.projectile.Projectile;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.player.GamePlayer;

public class SimpleBullet extends Projectile {
	
	private float damage;

	private float xSpeed,ySpeed;
	
	
	public SimpleBullet(GamePlayer source, Location location, float fireAngle, float speed) {
		super(source, location,fireAngle);	
		
		//calc x and y movement
		xSpeed=(float) (Math.cos(fireAngle)*speed);
		ySpeed=(float) (Math.sin(fireAngle)*speed);
		
		
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	@Override
	public void update(float delta) {
		//linear movement
		location.moveX(xSpeed);
		location.moveY(ySpeed);
	}

	@Override
	public Damage calcDamage(GamePlayer target) {
		//just calc plain damage, dont care where it hits!
		Damage dmg=new Damage(damage);
		dmg.setDamageDealer(source);
		return dmg;
	}

}
