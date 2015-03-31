package com.starbattle.server.main;

import java.io.IOException;
import java.net.InetAddress;

import com.starbattle.network.server.NetworkServer;
import com.starbattle.server.manager.MainServerManager;

public class StarbattleServer {

	
	public static void main(String[] args) {
		new StarbattleServer();
	}
	
	private NetworkServer server;
	private MainServerManager manager;
	
	public StarbattleServer()
	{
		server=new NetworkServer();
		
		try {
			
			//Create MainServer Manager
			manager=new MainServerManager(server);
			server.setServerListener(manager.createListener());		
			server.open(0,0);
			
//			server.open(56777, 56777);
			System.out.println("MainServer started at "+InetAddress.getLocalHost());
			int sc=server.getServer().getConnections()[0].getRemoteAddressTCP().getPort();
			System.out.println(sc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MainServerManager getManager() {
		return manager;
	}
	
	public void close()
	{
		server.close();
	}
	
}
