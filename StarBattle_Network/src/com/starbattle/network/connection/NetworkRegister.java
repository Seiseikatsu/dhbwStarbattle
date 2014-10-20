package com.starbattle.network.connection;

import java.util.ArrayList;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.starbattle.network.connection.objects.*;

public class NetworkRegister {

	private ArrayList<Class> register = new ArrayList<Class>();
	
	public NetworkRegister()
	{
		//add network classes to register 
		addNetworkClass(ArrayList.class);
		addNetworkClass(NP_Login.class);
		addNetworkClass(NP_Register.class);
		addNetworkClass(NP_StartAnswer.class);
		addNetworkClass(NP_Logout.class);
		addNetworkClass(NP_ResetEmail.class);
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
