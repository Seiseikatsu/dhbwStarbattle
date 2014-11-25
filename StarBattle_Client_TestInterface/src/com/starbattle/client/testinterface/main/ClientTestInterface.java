package com.starbattle.client.testinterface.main;

import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.testinterface.tester.ClientAutomate;

public class ClientTestInterface {

	
	public static ClientAutomate createNewTestClient()
	{
		StarBattleClient client=new StarBattleClient();
		return new ClientAutomate(client.getConnection(), client.getWindow());
	}
	
	public static ClientAutomate createClientAutomate(StarBattleClient client)
	{
		return new ClientAutomate(client.getConnection(), client.getWindow());
	}
}
