package com.starbattle.ingame.game;

import com.starbattle.ingame.game.bullets.BulletDesign;
import com.starbattle.ingame.game.bullets.BulletsContainer;
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
			showAnimation("JumpEffect", x, y);
			break;
		case PLASMA_GUN_SHOT:
			
			showAnimation("PlasmaGun", x, y,angle);
			fireBullet(BulletDesign.PLASMA_GUN_BULLET, x, y, angle, 0.5f);
			break;
		}
	}
	
	private void fireBullet(BulletDesign design, float x, float y, float angle, float speed)
	{
		BulletsContainer bullets=gameCore.getBulletsContainer();
		bullets.spawnBullet(new Location(x,y), design, angle, speed);
	}
	
	private void showAnimation(String animation, float x, float y)
	{
		ParticleContainer particleContainer=gameCore.getParticleContainer();
		particleContainer.spawnEffect(animation, new Location(x, y));
	}
	
	private void showAnimation(String animation, float x, float y, float angle)
	{
		ParticleContainer particleContainer=gameCore.getParticleContainer();
		particleContainer.spawnEffect(animation, new Location(x, y),angle);
	}
}
