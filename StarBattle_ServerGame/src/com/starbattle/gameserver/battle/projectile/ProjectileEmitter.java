package com.starbattle.gameserver.battle.projectile;

import java.util.List;

public interface ProjectileEmitter {
	
	public void spawnProjectile(Projectile projectile);
	
	public void spawnProjectiles(List<Projectile> projectiles);
	
}
