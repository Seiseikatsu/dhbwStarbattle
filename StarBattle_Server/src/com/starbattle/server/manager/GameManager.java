package com.starbattle.server.manager;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.exceptions.ServerStartException;
import com.starbattle.gameserver.main.BattleEndListener;
import com.starbattle.gameserver.main.BattleResults;
import com.starbattle.gameserver.main.BattleSettings;
import com.starbattle.gameserver.main.StarbattleGameControl;

public class GameManager {

	private List<StarbattleGameControl> games = new ArrayList<StarbattleGameControl>();
	private int gameID;
	
	public GameManager() {

	}

	public void openGame(BattleSettings battleSettings) {

		final StarbattleGameControl game = new StarbattleGameControl(gameID);
		games.add(game);
		try {
			game.start(battleSettings, new BattleEndListener() {

				@Override
				public void serverError() {

					removeGame(game);
				}

				@Override
				public void battleEnd(BattleResults results) {
					removeGame(game);

				}
			});
		} catch (ServerStartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameID++;
	}

	private void removeGame(StarbattleGameControl game) {
		games.remove(game);
	}

}
