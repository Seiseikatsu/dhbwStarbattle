package com.starbattle.gameserver.game.mode;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.player.GamePlayer;

public interface GameEndListener {

	public void playerWon(GamePlayer player);
	
	public void teamWon(Team team);
	
	public void noContest();
	
}
