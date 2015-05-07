package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.EffectTrigger;
import com.starbattle.gameserver.game.EffectTriggerFactory;
import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class PlayerWeaponHandler {

	private GamePlayer player;
	private EffectTrigger effectTrigger;

	public PlayerWeaponHandler(GamePlayer player, EffectTrigger effectTrigger) {
		this.player = player;
		this.effectTrigger = effectTrigger;
	}

	public void fireWeapon() {
		Location location = player.getLocation();
		float angle = player.getAttributes().getWeaponAngle();
		NP_TriggerEffect effect = EffectTriggerFactory.createEffect(location, TriggerEffects.PLASMA_GUN_SHOT, angle,player);
		effectTrigger.triggerEffect(effect);
	}

}
