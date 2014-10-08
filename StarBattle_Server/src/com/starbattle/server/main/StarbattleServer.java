package com.starbattle.server.main;

import java.io.IOException;

import com.starbattle.network.server.NetworkServer;
import com.starbattle.server.manager.MainServerManager;

public class StarbattleServer {

	
	public static void main(String[] args) {
		new StarbattleServer();
	}
	
	private NetworkServer server;
	
	public StarbattleServer()
	{
		server=new NetworkServer();
		try {
			
			//Create MainServer Manager
			MainServerManager manager=new MainServerManager(server);
			server.setServerListener(manager.createListener());		
			
			server.open(56777, 56777);
			System.out.println("MainServer started!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
