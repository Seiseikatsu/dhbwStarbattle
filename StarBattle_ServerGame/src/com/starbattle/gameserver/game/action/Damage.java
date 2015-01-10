package com.starbattle.gameserver.game.action;

import com.starbattle.gameserver.player.GamePlayer;

public class Damage {

	
	private GamePlayer damageDealer;
	private float damage;
	private float blowback;
	
	public float getBlowback() {
		return blowback;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public GamePlayer getDamageDealer() {
		return damageDealer;
	}
}
