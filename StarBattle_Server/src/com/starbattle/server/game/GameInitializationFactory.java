package com.starbattle.server.game;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.game.mode.impl.DeathMatch;
import com.starbattle.gameserver.game.mode.impl.DebugMode;
import com.starbattle.gameserver.game.mode.impl.TeamDeathMatch;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.main.BattleSettings;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.modes.GameModes;

public class GameInitializationFactory {

	private List<BattleParticipant> battleParticipants = new ArrayList<BattleParticipant>();
	private List<PlayerConnection> players = new ArrayList<PlayerConnection>();
	private List<String> names=new ArrayList<String>();
	private BattleSettings battleSettings = new BattleSettings();
	private GameModes mode;

	public GameInitializationFactory() {

	}

	public void addPlayer(PlayerConnection player, String displayName) {
		names.add(displayName);
		players.add(player);
	}

	public void setMap(String mapName) {
		battleSettings.setMapName(mapName);
	}

	public void initGameMode(GameModes mode, int pointLimit) {
		this.mode = mode;
		GameMode gameMode = null;
		switch (mode) {
		case TEAMDEATHMATCH:
			gameMode = new TeamDeathMatch(pointLimit);
			break;
		case DEATHMATCH:
			gameMode=new DeathMatch(pointLimit);
			break;
		case CAPTURETHEFLAG:
			break;
		case DEBUGMODE:
			gameMode=new DebugMode();
			break;
		}

		if (gameMode == null) {
			gameMode = new TeamDeathMatch(pointLimit);
		}
		battleSettings.setGameMode(gameMode);
	}

	public BattleInitialization build() {
		buildPlayerTeams();
		return new BattleInitialization(battleParticipants, battleSettings);
	}

	private void buildPlayerTeams() {
		int playerCount=players.size();
		Team[] team = battleSettings.getMode().initTeams(playerCount);
		for (int i = 0; i < playerCount; i++) {
			PlayerConnection player = players.get(i);
			String name=names.get(i);
			battleParticipants.add(new BattleParticipant(player,name, team[i]));
		}
	}
}
