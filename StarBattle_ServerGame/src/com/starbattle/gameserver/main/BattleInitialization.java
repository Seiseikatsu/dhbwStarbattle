package com.starbattle.gameserver.main;

import java.util.ArrayList;
import java.util.List;

public class BattleInitialization {

	private List<BattleParticipant> battleParticipants;
	private BattleSettings battleSettings;
	
	public BattleInitialization(List<BattleParticipant> battleParticipants, BattleSettings battleSettings)
	{
		this.battleParticipants=battleParticipants;
		this.battleSettings=battleSettings;
	}
	
	public List<BattleParticipant> getBattleParticipants() {
		return battleParticipants;
	}
	
	public BattleSettings getBattleSettings() {
		return battleSettings;
	}
}
