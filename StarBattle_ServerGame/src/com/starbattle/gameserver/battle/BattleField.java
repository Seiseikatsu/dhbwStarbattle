package com.starbattle.gameserver.battle;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.battle.projectile.Projectile;
import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.map.MapBorder;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class BattleField {

	private MapBorder mapBorder;
	private PlayerList players;
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private GameMode gameMode;
	private int projectileID;
	private EffectTrigger effectTrigger;
	private CollisionDetection collisionDetection;
	public final static int UPDATE_STEPS = 5;

	public BattleField(PlayerList player, GameMode mode, GameControl control) {
		this.mapBorder = control.getMapBorder();
		this.players = player;
		this.gameMode = mode;
		this.effectTrigger = control.getEffectTrigger();
		this.collisionDetection = control.getCollisionDetection();
	}

	public ProjectileEmitter createEmitter() {
		return new ProjectileEmitter() {
			@Override
			public void spawnProjectiles(List<Projectile> projetcs) {
				projectiles.addAll(projetcs);
			}

			@Override
			public int spawnProjectile(Projectile projectile) {
				projectiles.add(projectile);
				projectile.setId(projectileID);
				return projectileID++;
			}
		};
	}

	/**
	 * 
	 * This looks ugly but needs to be working fast.
	 * 
	 * because the projectiles have such a high speed, they just fly trough
	 * things and dont trigger collisions.
	 * Thats bad.
	 * 
	 * Solution:
	 * Bullets dont get updated 1 time on every game loop step.
	 * The update delta time (step-size) is cut in small steps.
	 * With UPDATE_STEP = 5  the update method takes 5 times as long as before!
	 * 
	 * So to avoid missing some important information (also the game looks better)
	 * the update method is called more often and with smaller stepsize.
	 * Instead of flying 1.0 units the projetile flies 5 * 0.2 units,
	 * so it cant fly trough 0.9 units big blocks!
	 * 
	 *  Another approach would be to use a linear solution for collision detection
	 *  and calc the exact hit area. This would need more time to calculate and would
	 *  be harder to code. This is a keep-it-simple implementation that should work 
	 *  good enough and also fast enough for our small data.
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		ArrayList<GamePlayer> playerList = players.getPlayers();
		int playerCount = playerList.size();

		int steps = UPDATE_STEPS;
		delta = delta / steps;
		for (int k = 0; k < steps; k++) {

			for (int i = 0; i < projectiles.size(); i++) {
				Projectile projectile = projectiles.get(i);
				projectile.update(delta);
				// check for collision with players
				Shape collision = projectile.getCollisonShape();
				boolean playerHit=false;
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
							playerHit=true;
						}
					}
				}

				// check for map collision
				Location location = projectile.getLocation();

				
				if (collisionDetection.locationInBlock(location)) {
					// trim to wall edge location
					destroy = true;
				}

				
				// check for removing because of map border
				if (destroy || !mapBorder.isInBorder(location)) {
					int id = projectile.getId();
					// trigger remove effect for clients
					if(playerHit)
					{
					NP_TriggerEffect effect = new NP_TriggerEffect();
					effect.effect_id = TriggerEffects.REMOVE_BULLET.ordinal();
					effect.source_id = id;
					effect.xpos = location.getXpos();
					effect.ypos = location.getYpos();
					effectTrigger.triggerEffect(effect);
					}
					// remove
					projectiles.remove(i);
				}
			}
		}
	}

}
