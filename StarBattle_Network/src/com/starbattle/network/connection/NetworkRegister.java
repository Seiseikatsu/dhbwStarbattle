package com.starbattle.network.connection;

import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.starbattle.network.connection.objects.*;
import com.starbattle.network.connection.objects.game.NP_ClientReady;
import com.starbattle.network.connection.objects.game.NP_GameEnd;
import com.starbattle.network.connection.objects.game.NP_GameException;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerData;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class NetworkRegister {

	private ArrayList<Class> register = new ArrayList<Class>();
	public final static int TCP_PORT=18400;
	public final static int UDP_PORT=18400;

	
	public NetworkRegister()
	{
		//add network classes to register 		
		addNetworkClass(ArrayList.class);
		addNetworkClass(String[].class);
		addNetworkClass(int[].class);
		
		addNetworkClass(NP_Login.class);
		addNetworkClass(NP_Register.class);
		addNetworkClass(NP_StartAnswer.class);
		addNetworkClass(NP_Logout.class);
		addNetworkClass(NP_ResetEmail.class);
		addNetworkClass(NP_ChatMessage.class);
		addNetworkClass(NP_FriendRequest.class);
		addNetworkClass(NP_FriendUpdate.class);
		addNetworkClass(NP_LobbyFriends.class);
		addNetworkClass(NP_HandleFriendRequest.class);
		addNetworkClass(NP_ChatException.class);
		addNetworkClass(NP_FriendRequestAnswer.class);
		addNetworkClass(NP_BattleResults.class);
		addNetworkClass(NP_GameModesList.class);
		addNetworkClass(NP_RequestGameModes.class);
		addNetworkClass(NP_ServerStop.class);
		addNetworkClass(NP_BattleResults.class);
		addNetworkClass(NP_CancelMatchQueue.class);
		addNetworkClass(NP_EnterMatchQueue.class);
		addNetworkClass(NP_GameEnd.class);
		addNetworkClass(NP_GameException.class);
		addNetworkClass(NP_GameStart.class);
		addNetworkClass(NP_GameUpdate.class);
		addNetworkClass(NP_PrepareGame.class);
		addNetworkClass(NP_TriggerEffect.class);
		addNetworkClass(NP_PlayerUpdate.class);
		addNetworkClass(NP_ClientReady.class);
		addNetworkClass(NP_PlayerData.class);
		addNetworkClass(NP_GameModeEntry.class);
		addNetworkClass(NP_ExitGame.class);

		addNetworkClass(NP_GameModeEntry[].class);
		addNetworkClass(NP_PlayerData[].class);
		addNetworkClass(NP_TriggerEffect[].class);
	}
	
	public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		for (Class c : register) {
			kryo.register(c);
		}

	}
	
	private void addNetworkClass(Class networkClass)
	{
		register.add(networkClass);
	}
	
}
