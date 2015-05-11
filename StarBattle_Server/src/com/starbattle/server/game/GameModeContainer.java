package com.starbattle.server.game;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.network.connection.objects.NP_GameModesList;

public class GameModeContainer {

	private List<PlayableGameMode> modes = new ArrayList<PlayableGameMode>();

	public GameModeContainer() {
		defaultInit();
	}

	private void defaultInit() {
		modes.add(new PlayableGameMode(GameModes.DEATHMATCH, "Testmap", 6));
		modes.add(new PlayableGameMode(GameModes.DEATHMATCH, "Testmap", 10));
		modes.add(new PlayableGameMode(GameModes.TEAMDEATHMATCH, "Testmap", 20));
	}

	public void removeMode(int nr) {
		modes.remove(nr);
	}

	public void addMode(PlayableGameMode mode) {
		modes.add(mode);
	}

	public List<PlayableGameMode> getModes() {
		return modes;
	}

	public NP_GameModesList getModeList() {
		NP_GameModesList list = new NP_GameModesList();
		int anz = GameModes.values().length;
		String[] names = new String[anz];
		String[] namesDisplay = new String[anz];
		for (int i = 0; i < anz; i++) {
			names[i] = GameModes.values()[i].name();
			namesDisplay[i] = GameModes.values()[i].getName();
		}
		list.modeDisplayNames = namesDisplay;
		list.modeNames = names;
		return list;
	}

}
