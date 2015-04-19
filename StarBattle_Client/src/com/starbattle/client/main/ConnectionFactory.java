package com.starbattle.client.main;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.NetworkConnectionListener;
import com.starbattle.client.main.error.BugSplashDialog;
import com.starbattle.network.connection.NetworkRegister;

public abstract class ConnectionFactory {

	
	public static NetworkConnection createConnection()
	{
		NetworkConnection connection = new NetworkConnection(new NetworkConnectionHandler());	
	//	connection.start("localhost", NetworkRegister.TCP_PORT,NetworkRegister.UDP_PORT);
		
		return connection;
	}
	
	// reacts if connection to server opened/closed
	private static class NetworkConnectionHandler implements NetworkConnectionListener {

		@Override
		public void onConnect() {
			System.out.println("Client connected to Server!");
		}

		@Override
		public void onDisconnect(String cause) {
		
				if(cause!=null)
				{
					//display disconnect cause
					BugSplashDialog.showMessage(cause);
				}
				
			//	loadingWindow.close();
				//window.open(ConnectionErrorView.VIEW_ID);
			
		}

	}
	
}
