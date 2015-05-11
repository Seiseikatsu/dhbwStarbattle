package com.starbattle.gameserver.battle.projectile;

import java.util.List;

public interface ProjectileEmitter {
	
	public int spawnProjectile(Projectile projectile);
	
	public void spawnProjectiles(List<Projectile> projectiles);
	
}
