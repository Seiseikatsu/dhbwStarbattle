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

	public void openClients(final NP_PrepareGame setup, final GameSendConnection sendConnection)
			throws GameClientException {
		for (final InGameClientDebug client : clients.values()) {
			Thread th = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("OPEN: "+client);
						client.open(setup, sendConnection);
					} catch (GameClientException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			th.start();
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
