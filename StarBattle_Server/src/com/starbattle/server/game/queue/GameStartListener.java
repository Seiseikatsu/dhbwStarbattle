package com.starbattle.server.game.queue;

import com.starbattle.gameserver.main.BattleInitialization;

public interface GameStartListener {

	public void startGame(BattleInitialization battleInitialization, GameQueue invoker);
	
}
