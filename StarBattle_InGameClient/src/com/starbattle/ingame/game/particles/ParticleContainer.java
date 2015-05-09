package com.starbattle.ingame.game.particles;

import java.util.HashMap;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.RenderSettings;

public class ParticleContainer {

	private HashMap<String, ParticleEffect> effects = new HashMap<String, ParticleEffect>();
	private int count;
	private RenderSettings renderSettings;

	public ParticleContainer() {

		// effects.put("Splash", new ParticleEffect("test"));
		// effects.put("Splash2", new ParticleEffect("test2"));
		effects.put("Air", new ParticleEffect("testAir"));
		effects.put("JumpEffect", new ParticleEffect("jumpEffect"));
		effects.put("PlasmaGun", new ParticleEffect("shotPlasmaGun"));
		effects.put("Death", new ParticleEffect("death"));
		effects.put("Damage", new ParticleEffect("test"));
	}

	private boolean canSpawnEffect() {
		if (renderSettings.isRenderParticles()) {
			int max = renderSettings.getMaxParticles();
			if (count < max) {
				return true;
			}
		}
		return false;
	}

	public void spawnEffect(String name, Location l) {
		if (canSpawnEffect()) {
			float x = l.getXpos();
			float y = l.getYpos();
			ParticleEffect effect = effects.get(name);
			if (effect == null) {
				System.err.println("Could not load Effect with name: " + name);
				return;
			}
			effect.spawnEffect(x, y);
		}
	}

	public void spawnEffect(String name, Location l, float angle) {
		if (canSpawnEffect()) {
			float x = l.getXpos();
			float y = l.getYpos();
			ParticleEffect effect = effects.get(name);
			if (effect == null) {
				System.err.println("Could not load Effect with name: " + name);
				return;
			}
			effect.spawnEffect(x, y, angle);
		}
	}

	public void render(Graphics g, Viewport viewport) {
		count = 0;
		for (ParticleEffect effect : effects.values()) {
			count += effect.render(viewport);

		}

	}

	public void update(int delta) {
		for (ParticleEffect effect : effects.values()) {
			effect.update(delta);
		}

	}

	public int getCount() {
		return count;
	}

	public void setRenderSettings(RenderSettings renderSettings) {
		this.renderSettings = renderSettings;
	}
}
