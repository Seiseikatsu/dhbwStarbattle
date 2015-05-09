package com.starbattle.ingame.resource;

import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.resource.player.ResourceException;

public class SoundContainer {

	public final static String PATH = ResourceContainer.PATH + "sounds/";

	private HashMap<String, Sound> sounds = new HashMap<String, Sound>();

	public SoundContainer() {

	}

	public void loadSounds() throws ResourceException {
		loadSound("hit");
		loadSound("jump");
		loadSound("explosion");
		loadSound("lazer");
	}

	// anzahl blöcke wie weit der spieler hören kann
	private static float SOUND_HEAR_DISTANCE = 15;

	// calcs sound volume from distance
	public float getSoundVolume(Location myLocation, Location soundLocation) {
		float distance = soundLocation.getDistanceTo(myLocation);
		float volume = 1 - distance / SOUND_HEAR_DISTANCE;
		if (volume < 0) {
			volume = 0;
		} else if (volume > 1) {
			volume = 1;
		}
		return volume;
	}

	private void loadSound(String name) throws ResourceException {
		String file = PATH + name + ".wav";
		try {
			Sound sound = new Sound(file);
			sounds.put(name, sound);
		} catch (SlickException e) {
			e.printStackTrace();
			throw new ResourceException("Failed to load Sound: " + file);
		}
	}

	public void playSound(String sound) {
		if (sounds.containsKey(sound)) {
			Sound snd = sounds.get(sound);
			snd.play();
		}
	}

	public void playSound(String sound, float volume) {
		if (volume > 0) {
			if (sounds.containsKey(sound)) {
				Sound snd = sounds.get(sound);
				snd.play(1, volume);
			}
		}
	}

}
