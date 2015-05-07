package com.starbattle.ingame.game.particles;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitter.SimpleValue;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.viewport.Viewport;

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
				if (image != null) {
					emitter.setImageName(path + "/images/" + image);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void spawnEffect(float x, float y) {
		try {
			ParticleSystem spawn = system.duplicate();
			spawn.setRemoveCompletedEmitters(true);
			spawn.setPosition(x, y);
			entities.add(spawn);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	

	public void spawnEffect(float x, float y, float angle) {
		try {
			ParticleSystem spawn = system.duplicate();
			spawn.setRemoveCompletedEmitters(true);
			spawn.setPosition(x, y);
		//	System.out.println("angle: "+angle);
			for(int i=0; i<spawn.getEmitterCount(); i++)
			{
				ConfigurableEmitter emitter = (ConfigurableEmitter) system.getEmitter(i);
				emitter.angularOffset.setValue((float) Math.toDegrees(angle)+90);
			}
			entities.add(spawn);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void update(int delta) {
		for (int i = 0; i < entities.size(); i++) {
			ParticleSystem system = entities.get(i);
			system.update(delta);

			if (system.getParticleCount() == 0) {
				entities.remove(i);
			}
		}
	}

	public int render(Viewport viewport) {
		int count = 0;
		for (int i=0; i<entities.size(); i++) {
			ParticleSystem system = entities.get(i);
			float x = system.getPositionX();
			float y = system.getPositionY();
			Location l = new Location(x, y);
			Location view = viewport.getScreenLocation(l);
			system.render(view.getXpos(), view.getYpos());
			count++;
		}
		return count;
	}
}
