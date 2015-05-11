package com.starbattle.gameserver.battle.projectile.impl;

import org.newdawn.slick.geom.Ellipse;

import com.starbattle.gameserver.battle.projectile.Projectile;
import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.EffectTriggerFactory;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class SimpleBullet extends Projectile {
	
	private float damage;

	private float xSpeed,ySpeed;
	
	
	public SimpleBullet(GamePlayer source, Location location, float fireAngle, float speed, EffectTrigger effectTrigger) {
		super(source, location,fireAngle,effectTrigger);	
		
		//calc x and y movement
		xSpeed=(float) (Math.cos(fireAngle)*speed);
		ySpeed=(float) (Math.sin(fireAngle)*speed);
		float radius=0.3f;
		this.collisonShape=new Ellipse(location.getXpos(), location.getYpos(), radius, radius);

	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	@Override
	public void update(float delta) {
		//linear movement
		location.moveX(xSpeed*delta);
		location.moveY(ySpeed*delta);
		//update collision shape
		collisonShape.setCenterX(location.getXpos());
		collisonShape.setCenterY(location.getYpos());
		
	}

	@Override
	public Damage onHit(GamePlayer target) {
		//just calc plain damage, dont care where it hits!
		Damage dmg=new Damage(damage);
		dmg.setDamageDealer(source);
		
		//trigger hit animation
		NP_TriggerEffect effect = EffectTriggerFactory.createEffect(location, TriggerEffects.DAMAGE_HIT, fireAngle, source);
		effectTrigger.triggerEffect(effect);
		
		return dmg;
	}

}
