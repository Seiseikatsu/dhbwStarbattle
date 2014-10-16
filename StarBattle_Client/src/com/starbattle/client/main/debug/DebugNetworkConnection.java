package com.starbattle.client.main.debug;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.NetworkConnectionListener;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.client.SendServerListener;

public class DebugNetworkConnection extends NetworkConnection {

	public DebugNetworkConnection(NetworkConnectionListener networkConnectionListener) {
		super(networkConnectionListener);
		System.out.println("Client DEBUG Modus| Simulate debug connection");
	}
	
	@Override
	public SendServerConnection getSendConnection() {
	
		return new DebugSendConnection(null);
	}

	private class DebugSendConnection extends SendServerConnection{

		public DebugSendConnection(SendServerListener listener) {
			super(listener);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void sendTCP(Object message) {
			
			System.out.println("Client DEBUG Modus| Send TCP message " + message.getClass().getSimpleName());
		}
		
		@Override
		public void sendUDP(Object message) {

			System.out.println("Client DEBUG Modus| Send UDP message "+message.getClass().getSimpleName());
	
		}
		
	}
	
	
	
	
}
