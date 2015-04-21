package com.starbattle.debug.client;

import java.util.HashMap;

import com.starbattle.debug.game.PlayerList;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class ClientsContainer {

	private HashMap<String, InGameClientDebug> clients = new HashMap<String, InGameClientDebug>();

	public ClientsContainer(PlayerList player) {

		for (int i = 0; i < player.getCount(); i++) {
			InGameClientDebug client = new InGameClientDebug();
			String account = player.getAccounts().get(i);
			clients.put(account, client);
		}
	}

	public void openClients(NP_PrepareGame setup, GameSendConnection sendConnection) throws GameClientException {
		for (InGameClientDebug client : clients.values()) {
			client.open(setup, sendConnection);
		}
	}

	public void sendToAll(Object object) {
		for (InGameClientDebug client : clients.values()) {
			client.receiveObject(object);
		}
	}

	public void sendTo(String name, Object object) {
		clients.get(name).receiveObject(object);
	}

}
