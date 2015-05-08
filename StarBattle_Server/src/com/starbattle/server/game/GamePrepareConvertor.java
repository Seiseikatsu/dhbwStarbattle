package com.starbattle.server.game;

import java.util.List;

import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public abstract class GamePrepareConvertor {

	public static NP_PrepareGame createPrepareGame(BattleInitialization battleInitialization) {

		NP_PrepareGame np = new NP_PrepareGame();
		np.mapName = battleInitialization.getBattleSettings().getMapName();
		np.teamGame = true;
		List<BattleParticipant> pls = battleInitialization.getBattleParticipants();
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
