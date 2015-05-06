package com.starbattle.gameserver.game;

import com.starbattle.gameserver.game.physics.Location;
import com.starbattle.network.connection.objects.constant.TriggerEffects;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public abstract class EffectTriggerFactory {

	
	public static NP_TriggerEffect createEffect(Location location, TriggerEffects eft, float angle)
	{
		NP_TriggerEffect effect=new NP_TriggerEffect();
		effect.direction_angle=angle;
		effect.xpos=location.getXpos();
		effect.ypos=location.getYpos();
		effect.effect_id=eft.ordinal();
		return effect;
	}
	
	
}
