package com.starbattle.server.modes;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.starbattle.network.connection.objects.NP_GameModeEntry;
import com.starbattle.network.connection.objects.NP_GameModesList;

public class GameModeContainer {

	private ModesReader modesReader = new ModesReader();
	private ModeSettings gameModes;

	public GameModeContainer() {
		loadModes();
	}

	public void loadModes() {
		System.out.println("Loading mode settings...");
		gameModes = modesReader.readSettings();
		if (gameModes == null) {
			defaultInit();
		} else {
			// check modes if valid
			checkInvalidSettings();
		}
	}

	private void checkInvalidSettings() {
		Iterator<ModeSettingsEntry> modes = gameModes.getModes().iterator();
		while (modes.hasNext()) {
			ModeSettingsEntry mode = modes.next();
			if (mode.getMode() == null) {
				// mode not valie, remove complete mode settings
				System.err.println("Invalid mode-identifier found! Check enum GameModes for available mode-names.");
				modes.remove();
			} else {
				// check for invalid maps
				Iterator<String> maps = mode.getMaps().iterator();
				while (maps.hasNext()) {
					String mapName = maps.next();
					if (!isMapNameValid(mapName)) {
						// no map found for that name
						// remove map
						System.err.println("Could not found map \"" + mapName + "\"!");
						maps.remove();
					}
				}
			}
		}
		// print warning
		int size = gameModes.getModes().size();
		if (size == 0) {
			System.err.println("Warning: No valid gamemode was found!");
		} else {
			System.out.println("Set " + size + " gamemode(s)!");
		}

	}

	private boolean isMapNameValid(String name) {
		File file = new File("resource/maps/" + name + ".tmx");
		return file.exists() && !file.isDirectory();
	}

	public void saveModes() {
		modesReader.writeSettings(gameModes);
	}

	private void defaultInit() {
		System.err.println("Server could not load mode settings!");
		System.err.println("=> Init default modes...");

		gameModes = new ModeSettings();
		List<ModeSettingsEntry> entries = new ArrayList<ModeSettingsEntry>();
		// add one debug/default mode
		ModeSettingsEntry entry = new ModeSettingsEntry();
		entry.setMode(GameModes.TEAMDEATHMATCH);
		List<String> maps = new ArrayList<String>();
		maps.add("testmap");
		entry.setMaps(maps);
		entries.add(entry);
		gameModes.setModes(entries);
	}

	public void listModes() {
		System.out.println("GameModes - List:");
		for (ModeSettingsEntry entry : gameModes.getModes()) {
			String mode = entry.getMode().getName();
			String maps = "";
			for (String map : entry.getMaps()) {
				maps += map + "  ";
			}
			System.out.println("> " + mode + " / Maps: " + maps);
		}
	}

	public NP_GameModesList getModeList() {
		NP_GameModesList list = new NP_GameModesList();
		List<ModeSettingsEntry> modes = gameModes.getModes();
		int entries = modes.size();
		NP_GameModeEntry[] modeEntries = new NP_GameModeEntry[entries];
		for (int i = 0; i < entries; i++) {
			ModeSettingsEntry mode = modes.get(i);
			NP_GameModeEntry modeEntry = new NP_GameModeEntry();
			// set mode settings
			modeEntry.mode = mode.getMode().name();
			modeEntry.modeDisplayName = mode.getMode().getName();
			modeEntry.maps = mode.getMaps().toArray(new String[0]);
			modeEntries[i] = modeEntry;
		}
		list.modes = modeEntries;
		return list;
	}

}
