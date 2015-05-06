package com.starbattle.ingame.game;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;
import com.starbattle.network.connection.objects.constant.TriggerEffects;

public class TriggerEffectsProcessor {

	private GameCore gameCore;
	
	public TriggerEffectsProcessor(GameCore gameCore) {
		this.gameCore=gameCore;
	}
	
	
	public void processEffects(NP_TriggerEffect[] effects)
	{
		if(effects!=null){
						
			for(NP_TriggerEffect effect: effects)
			{
				process(effect);
			}			
		}
	}
	
	private void process(NP_TriggerEffect effect)
	{
		TriggerEffects id=TriggerEffects.values()[effect.effect_id];
		float x=effect.xpos;
		float y=effect.ypos;
		float angle=effect.direction_angle;
		
		
		switch(id)
		{
		case JUMP_ANIMATION:
			showAnimation("JumpEffect", x, y, angle);
			break;
		}
	}
	
	private void showAnimation(String animation, float x, float y, float angle)
	{
		ParticleContainer particleContainer=gameCore.getParticleContainer();
		particleContainer.spawnEffect(animation, new Location(x, y));
	}
}
