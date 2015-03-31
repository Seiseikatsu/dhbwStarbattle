package com.starbattle.gameserver.main;

public interface BattleEndListener {

	public void battleEnd(BattleResults result);
	
	public void serverError();
	
}
