package com.starbattle.network.connection;

import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.starbattle.network.connection.objects.*;

public class NetworkRegister {

	private ArrayList<Class> register = new ArrayList<Class>();
	public final static int TCP_PORT=18400;
	public final static int UDP_PORT=18400;

	
	public NetworkRegister()
	{
		//add network classes to register 
		addNetworkClass(ArrayList.class);
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
