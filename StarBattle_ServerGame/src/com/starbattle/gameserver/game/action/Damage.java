package com.starbattle.gameserver.game.action;

import com.starbattle.gameserver.player.GamePlayer;

public class Damage {

	
	private GamePlayer damageDealer;
	private float damage;
	private float knockback;
	
	public Damage(float damage)
	{
		this.damage=damage;
		knockback=0;
	}
	
	public void setDamageDealer(GamePlayer damageDealer) {
		this.damageDealer = damageDealer;
	}
	
	public void setKnockback(float knockback) {
		this.knockback = knockback;
	}
	
	public float getKnockback() {
		return knockback;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public GamePlayer getDamageDealer() {
		return damageDealer;
	}

}
