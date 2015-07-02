package com.starbattle.client.connection.listener;

import com.starbattle.network.connection.objects.NP_BattleResults;

public interface NetworkBattleResultsListener {

	
	public void receivedBattleResults(NP_BattleResults results);
	
	
}
