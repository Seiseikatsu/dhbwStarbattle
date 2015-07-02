package com.starbattle.server.game;

import com.starbattle.gameserver.main.BattleResults;
import com.starbattle.gameserver.main.PlayerResults;
import com.starbattle.network.connection.objects.NP_BattleResults;

public abstract class BattleResultsUtil {

	public static NP_BattleResults generateResults(BattleResults results) {
		NP_BattleResults r = new NP_BattleResults();

		int anz = results.getResults().size();

		r.names = new String[anz];
		r.points = new int[anz];
		r.teams = new int[anz];
		r.winnerID = results.getWinnerID();
		r.teamGame = results.isTeamGame();

		for (int i = 0; i < anz; i++) {
			PlayerResults pr = results.getResults().get(i);
			r.names[i] = pr.getDisplayName();
			r.points[i] = pr.getPoints();
			r.teams[i] = pr.getTeam();
		}

		return r;
	}

}
