package com.starbattle.client.testinterface.main;

import java.util.ArrayList;

import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.testinterface.tester.ClientAutomate;

public class ClientTestInterface {

	public static float toleranceSeconds = 5;
	public static float networkTimeout = 10;
	public static float stepDelay = 1f;
	public static float shutdownDelaySeconds = 2;
	private static ArrayList<ClientAutomate> simulations = new ArrayList<ClientAutomate>();

	public static ClientAutomate createNewTestClient() {
		StarBattleClient client = new StarBattleClient();
		ClientAutomate simulation = new ClientAutomate(client);
		simulations.add(simulation);
		return simulation;
	}

	public static ClientAutomate createClientAutomate(StarBattleClient client) {
		ClientAutomate simulation = new ClientAutomate(client);
		simulations.add(simulation);
		return simulation;
	}

	public static void shutdown() {
		try {
			Thread.sleep((long) (1000 * shutdownDelaySeconds));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (ClientAutomate client : simulations) {
			if (client != null) {
				client.shutdown();
			}
		}
	}
}
