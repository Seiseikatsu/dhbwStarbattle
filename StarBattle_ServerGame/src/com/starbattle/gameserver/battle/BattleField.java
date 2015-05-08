package com.starbattle.gameserver.battle;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.battle.projectile.Projectile;
import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.map.MapBorder;

public class BattleField {

	private MapBorder mapBorder;
	private List<Projectile> projectiles = new ArrayList<Projectile>();

	public BattleField(MapBorder mapBorder) {
		this.mapBorder = mapBorder;
	}

	public ProjectileEmitter createEmitter() {
		return new ProjectileEmitter() {
			@Override
			public void spawnProjectiles(List<Projectile> projetcs) {
				projectiles.addAll(projetcs);
			}

			@Override
			public void spawnProjectile(Projectile projectile) {
				projectiles.add(projectile);
			}
		};
	}

	public void update(float delta) {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			projectile.update(delta);
			// check for removing because of map border
			if (!mapBorder.isInBorder(projectile.getLocation())) {
				// remove
				projectiles.remove(i);
			}
		}
	}

}
