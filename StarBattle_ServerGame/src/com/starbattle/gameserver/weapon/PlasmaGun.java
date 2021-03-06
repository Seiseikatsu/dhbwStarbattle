package com.starbattle.gameserver.weapon;

import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.battle.projectile.impl.SimpleBullet;
import com.starbattle.gameserver.game.EffectTriggerFactory;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class PlasmaGun extends Weapon {

	private float damage=5;
	
	public PlasmaGun(GamePlayer player, GameControl gameControl, ProjectileEmitter emitter) {
		super(player, gameControl, emitter);

		// 20 schuss, kurze schusspause zwischen schüssen
		init(20, 5);
		// schüsse werden ständig automatisch neugeladen (damit man immer eine
		// basis waffe hat mit munition)
		setAutoReload(50);
	}

	@Override
	protected void onFiring(Location location, float fireAngle) {

		// create simple projectile
		float speed = 0.75f;
		SimpleBullet projectile = new SimpleBullet(owner, location, fireAngle, speed,effectTrigger);
		projectile.setDamage(damage);
		int projectileID=emitter.spawnProjectile(projectile);
		
		// trigger effect for players
		NP_TriggerEffect effect = EffectTriggerFactory.createEffect(location, TriggerEffects.PLASMA_GUN_SHOT, fireAngle,owner);
		effect.extra_id=projectileID;
		effectTrigger.triggerEffect(effect);
	}

}
