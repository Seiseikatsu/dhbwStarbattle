package com.starbattle.debug.game;

import com.starbattle.debug.client.ClientsContainer;
import com.starbattle.debug.server.BattleInitDebug;
import com.starbattle.debug.server.SendClientsConnection;
import com.starbattle.debug.server.ServerGameDebug;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.ingame.main.GameClientException;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

/**
 * 
 * Target is to run a complete game without network logic. Original projects are
 * used, just interface implementation without a network and client.
 * 
 * @author RoO
 *
 */

public class GameDebugger {



	private ServerGameDebug server;
	private ClientsContainer clients;
	private PlayerList playerList;


	
	public void create(String mapName, PlayerList player) throws GameClientException
	{
		this.playerList=player;
		// start server
		server = new ServerGameDebug();

		SendClientsConnection sendClientsConnection = new SendToClients();
		BattleInitialization init = BattleInitDebug.createInit(mapName, player, sendClientsConnection);
		server.setSendClientsConnection(sendClientsConnection);
	
		// start clients
		NP_PrepareGame setup = BattleInitDebug.createClientInit(init);
		clients = new ClientsContainer(player);
		clients.openClients(setup, new SendToServer());	
		server.startGame(init);
	}
	
	


	private class SendToServer implements GameSendConnection {

		@Override
		public void sendTCP(Object object) {
			server.receive(object,playerList.getAccounts().get(0));
		}
		
		public void sendUDP(Object object) {
			server.receive(object,playerList.getAccounts().get(0));
		}
		
	}

	private class SendToClients implements SendClientsConnection {

		@Override
		public void sendToClients(Object object) {
			clients.sendToAll(object);
		}

		@Override
		public void sendToClient(String name, Object object) {
			clients.sendTo(name, object);
		}

	}
}
