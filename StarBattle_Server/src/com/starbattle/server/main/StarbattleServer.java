package com.starbattle.server.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.network.connection.NetworkRegister;
import com.starbattle.network.connection.objects.NP_ServerStop;
import com.starbattle.network.server.NetworkServer;
import com.starbattle.server.console.ServerConsoleControl;
import com.starbattle.server.console.output.TextFileReader;
import com.starbattle.server.manager.MainServerManager;

public class StarbattleServer {

	public static void main(String[] args) throws AccountException {
		new StarbattleServer();
	}

	private NetworkServer server;
	private MainServerManager manager;
	private ServerConsoleControl consoleControl;

	public StarbattleServer() throws AccountException {
		// Display Console output
		System.out.println(TextFileReader.readTextFile("IntroOutput.txt"));

		server = new NetworkServer();

		try {

			// Create MainServer Manager
			manager = new MainServerManager(server);
			server.setServerListener(manager.createListener());
			// server.open(0,0);

			server.open(NetworkRegister.TCP_PORT, NetworkRegister.UDP_PORT);

			// open console controle
			consoleControl = new ServerConsoleControl(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shutdown(String message) {
		NP_ServerStop serverStop = new NP_ServerStop();
		serverStop.shutdown_Message = message;
		server.getSendConnection().sendTCPToAll(serverStop);
		// stop managers
		manager.close();
		// close server
		server.close();
		// close console
		consoleControl.close();
		// Process is finished, all threads stopped
		System.out.println("Server closed with information: \""+message+"\"");
	}

	public MainServerManager getManager() {
		return manager;
	}

	public NetworkServer getServer() {
		return server;
	}



}
