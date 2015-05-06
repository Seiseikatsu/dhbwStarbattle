package com.starbattle.ingame.game.particles;

import java.util.HashMap;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.viewport.Viewport;

public class ParticleContainer {

	private HashMap<String, ParticleEffect> effects = new HashMap<String, ParticleEffect>();
	private int count;

	public ParticleContainer() {

		effects.put("Splash", new ParticleEffect("test"));
		effects.put("Splash2", new ParticleEffect("test2"));
		effects.put("Air", new ParticleEffect("testAir"));
		effects.put("JumpEffect", new ParticleEffect("jumpEffect"));
	}

	public void spawnEffect(String name, Location l) {
		float x = l.getXpos();
		float y = l.getYpos();
		ParticleEffect effect = effects.get(name);
		if(effect==null)
		{
			System.err.println("Could not load Effect with name: "+name);
			return;
		}
		effect.spawnEffect(x, y);
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
}
