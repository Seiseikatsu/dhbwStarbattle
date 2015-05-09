package com.starbattle.gameserver.battle;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.battle.projectile.Projectile;
import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.map.MapBorder;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;

public class BattleField {

	private MapBorder mapBorder;
	private PlayerList players;
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private GameMode gameMode;

	public BattleField(MapBorder mapBorder, PlayerList player, GameMode mode) {
		this.mapBorder = mapBorder;
		this.players = player;
		this.gameMode = mode;
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
		ArrayList<GamePlayer> playerList = players.getPlayers();
		int playerCount = playerList.size();

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			projectile.update(delta);
			// check for collision with players
			Shape collision = projectile.getCollisonShape();

			boolean destroy = false;
			for (int h = 0; h < playerCount; h++) {

				GamePlayer target = playerList.get(h);
				if (target.isAlive()) {
					// check if enemies
					GamePlayer source = projectile.getSource();

					if (source != null) {
						Team targetTeam = target.getAttributes().getTeam();
						Team sourceTeam = source.getAttributes().getTeam();
						// dont do friendly fire!
						if (targetTeam.isSameTeam(sourceTeam)) {
							continue;
						}
					}

					Shape playerCollision = target.getCollisionShape();
					if (collision.intersects(playerCollision)) {
						// => hit
						Damage damage = projectile.onHit(target);
						// do damage
						gameMode.onTakingDamage(target, damage);
						destroy = true;
					}
				}
			}
			// check for removing because of map border
			if (destroy || !mapBorder.isInBorder(projectile.getLocation())) {
				// remove
				projectiles.remove(i);
			}
		}
	}

}
