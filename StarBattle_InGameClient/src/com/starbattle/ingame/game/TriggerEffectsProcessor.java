package com.starbattle.ingame.game;

import com.starbattle.ingame.game.bullets.BulletDesign;
import com.starbattle.ingame.game.bullets.BulletsContainer;
import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.resource.SoundContainer;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;
import com.starbattle.network.connection.objects.constant.TriggerEffects;

public class TriggerEffectsProcessor {

	private GameCore gameCore;

	public TriggerEffectsProcessor(GameCore gameCore) {
		this.gameCore = gameCore;
	}

	public void processEffects(NP_TriggerEffect[] effects) {
		if (effects != null) {

			for (NP_TriggerEffect effect : effects) {
				process(effect);
			}
		}
	}

	private void process(NP_TriggerEffect effect) {
		TriggerEffects id = TriggerEffects.values()[effect.effect_id];
		float x = effect.xpos;
		float y = effect.ypos;
		int source = effect.source_id;
		float angle = effect.direction_angle;
		Location effectLocation = new Location(x, y);

		switch (id) {
		case GROUND_JUMP:
			playSound("jump", effectLocation);
			break;
		case AIR_JUMP:
			showAnimation("JumpEffect", effectLocation);
			playSound("jump", effectLocation);
			break;
		case PLASMA_GUN_SHOT:
			// show gun firing
			PlayerObject player = gameCore.getPlayers().getPlayer(source);
			player.getDisplay().firedWeapon();
			showAnimation("PlasmaGun", effectLocation, angle);
			int bulletID=effect.extra_id;
			fireBullet(BulletDesign.PLASMA_GUN_BULLET, effectLocation, angle, 0.75f,bulletID);
			playSound("lazer", effectLocation);
			break;
		case DEATH_ANIMATION:
			showAnimation("Death", effectLocation);
			playSound("explosion", effectLocation);
			break;
		case DAMAGE_HIT:
			showAnimation("Damage", effectLocation);
			playSound("hit", effectLocation);
			break;
		case REMOVE_BULLET:
			showAnimation("Damage", effectLocation);
			
			gameCore.getBulletsContainer().removeBullet(source);
			break;
		}
	}
	


	private void playSound(String name, Location soundLocation) {
		Location myLocation = gameCore.getPlayers().getMyPlayer().getLocation();
		SoundContainer sounds = gameCore.getResourceContainer().getSounds();
		float volume = sounds.getSoundVolume(myLocation, soundLocation);
		sounds.playSound(name, volume);
	}

	private void fireBullet(BulletDesign design, Location location, float angle, float speed, int id) {
		BulletsContainer bullets = gameCore.getBulletsContainer();
		bullets.spawnBullet(id,location, design, angle, speed);
	}

	private void showAnimation(String animation, Location location) {
		ParticleContainer particleContainer = gameCore.getParticleContainer();
		particleContainer.spawnEffect(animation, location);
	}

	private void showAnimation(String animation, Location location, float angle) {
		ParticleContainer particleContainer = gameCore.getParticleContainer();
		particleContainer.spawnEffect(animation, location, angle);
	}
}
