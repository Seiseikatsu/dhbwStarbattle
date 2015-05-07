package com.starbattle.gameserver.game;

import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public abstract class EffectTriggerFactory {

	public static NP_TriggerEffect createEffect(Location location, TriggerEffects eft, float angle, int source) {
		NP_TriggerEffect effect = new NP_TriggerEffect();
		effect.direction_angle = angle;
		effect.xpos = location.getXpos();
		effect.ypos = location.getYpos();
		effect.effect_id = eft.ordinal();
		effect.source_id = source;
		return effect;
	}

	public static NP_TriggerEffect createEffect(Location pos, TriggerEffects eft, float angle, GamePlayer gamePlayer) {
		return createEffect(pos, eft, angle, gamePlayer.getAttributes().getPlayerID());
	}

}
