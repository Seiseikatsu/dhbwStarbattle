package com.starbattle.ingame.game.particles;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ParticleEffect {
	private static final String path = "com/starbattle/ingame/game/particles/effects/";
	private ParticleSystem system;
	private ArrayList<ParticleSystem> entities = new ArrayList<ParticleSystem>();

	public ParticleEffect(String xml) {
		try {
			system = ParticleIO.loadConfiguredSystem(path + xml + ".xml");
			for (int i = 0; i < system.getEmitterCount(); i++) {
				ConfigurableEmitter emitter = (ConfigurableEmitter) system.getEmitter(i);
				String image = emitter.imageName;
				emitter.setImageName(path + "/images/" + image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void spawnEffect(float x, float y) {
		try {
			ParticleSystem spawn = system.duplicate();
			spawn.setPosition(x, y);
			entities.add(spawn);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void update(int delta) {
		for (int i = 0; i < entities.size(); i++) {
			ParticleSystem system = entities.get(i);
			system.update(delta);
	
			if (system.getParticleCount()==0) {
				entities.remove(i);
			}
		}
	}

	public int render() {
		int count=0;
		for (ParticleSystem system : entities) {
			system.render();
			count++;
		}
		return count;
	}
}
