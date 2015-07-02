package com.starbattle.debug.server;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.debug.game.PlayerList;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.mode.impl.DeathMatch;
import com.starbattle.gameserver.game.mode.impl.DebugMode;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.main.BattleSettings;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;
import com.starbattle.network.server.PlayerConnection;

public class BattleInitDebug {

	public final static int GAME_ID = 0;

	public static BattleInitialization createInit(String mapName,PlayerList player, SendClientsConnection sendConnection) {

		BattleSettings settings = new BattleSettings();
		settings.setMapName(mapName);
		settings.setGameMode(new DebugMode());

		List<BattleParticipant> battleParticipants = new ArrayList<BattleParticipant>();

		for (int i = 0; i < player.getCount(); i++) {
			String account = player.getAccounts().get(i);
			String name = player.getNames().get(i);

			PlayerConnection connection = new DebugPlayerConnection(sendConnection);
			connection.setAccountName(account);
			connection.setGameID(GAME_ID);
			Team team;
			if (i % 2 == 0) {
				team = Team.BLUE_TEAM;
			} else {
				team = Team.RED_TEAM;
			}
			team=Team.NO_TEAM;
			BattleParticipant bp = new BattleParticipant(connection, name, team);
			battleParticipants.add(bp);
		}

		BattleInitialization init = new BattleInitialization(battleParticipants, settings);
		return init;
	}

	public static NP_PrepareGame createClientInit(BattleInitialization init) {
		NP_PrepareGame np = new NP_PrepareGame();
		np.mapName = init.getBattleSettings().getMapName();
		np.teamGame = true;
		List<BattleParticipant> pls = init.getBattleParticipants();
		int c = pls.size();
		int[] teams = new int[c];
		String[] names = new String[c];
		for (int i = 0; i < c; i++) {
			teams[i] = pls.get(i).getTeam().getTeamId();
			names[i] = pls.get(i).getDisplayName();
		}
		np.teams = teams;
		np.playerNames = names;
		return np;
	}

}
