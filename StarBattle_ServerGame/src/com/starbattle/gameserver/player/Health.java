package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.action.Damage;

public class Health {

	private float health,maxhealth;
	private boolean dead=false;
	private HealthListener healthListener;
	
	public Health(float health)
	{
		setMaxhealth(health);
	}
	
	public void setHealthListener(HealthListener healthListener) {
		this.healthListener = healthListener;
	}
	
	public void revive()
	{
		health=maxhealth;
		dead=false;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void takeDamage(Damage damage)
	{
		float dam=damage.getDamage();
		health-=dam;
		if(health>maxhealth)
		{
			health=maxhealth;
		}
		if(health<=0)
		{
			kill();
		}
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public void setMaxhealth(float maxhealth) {
		this.maxhealth = maxhealth;
		this.health=maxhealth;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getMaxhealth() {
		return maxhealth;
	}

	public void kill() {
		health=0;
		dead=true;
		healthListener.playerKilled();
	}

	public float getHealthPercent() {
		return health/maxhealth;
	}
	
}
