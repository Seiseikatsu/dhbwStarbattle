package com.starbattle.server.manager;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.server.PlayerConnection;

public class GameLoading {

	private List<PlayerConnection> players = new ArrayList<PlayerConnection>();
	private boolean[] finishedLoading;
	private boolean gameStarted=false;
	
	public GameLoading(BattleInitialization init) {

		// add players
		for (BattleParticipant btp : init.getBattleParticipants()) {
			players.add(btp.getConnection());
		}

		finishedLoading = new boolean[players.size()];
	}

	public void playerFinishedLoading(PlayerConnection player) {
		
	//	System.out.println("player finished loading; "+player.getAccountName());
		int index = players.indexOf(player);
		if (index != -1) {
			finishedLoading[index] = true;
		}

		// check if everyone is finished (prevent starting again)
		if (everyoneFinishedLoading()&&gameStarted==false) {
			gameStarted=true;
			// send every player that the game starts now
			NP_GameStart start = new NP_GameStart();
			for (int i = 0; i < players.size(); i++) {
				PlayerConnection pl = players.get(i);
			//	System.out.println("Send start to: "+pl.getAccountName());
				pl.sendTCP(start);
			}
			
		}
	}
	
	public boolean isGameStarted() {
		return gameStarted;
	}

	private boolean everyoneFinishedLoading() {
		for (boolean finished : finishedLoading) {
			if (finished == false) {
				return false;
			}
		}
		return true;
	}
}
