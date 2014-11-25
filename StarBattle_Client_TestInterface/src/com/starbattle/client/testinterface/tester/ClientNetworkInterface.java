package com.starbattle.client.testinterface.tester;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkCommunctionListener;
import com.starbattle.client.testinterface.exceptions.NetworkTimeoutException;

public class ClientNetworkInterface {

	private NetworkConnection connection;
	private Object networkObject;
	
	public ClientNetworkInterface(NetworkConnection networkConnection)
	{
		this.connection=networkConnection;
		connection.setNetworkCommunctionListener(new NetworkCommunctionListener() {

			@Override
			public void received(Object object) {
				networkObject = object;
			}
		});
	}
	
	public void sendTCP(Object object)
	{
		connection.getSendConnection().sendTCP(object);
	}
	
	public void sendUDP(Object object)
	{
		connection.getSendConnection().sendUDP(object);
	}
	
	public Object waitForNetworkReceive(final Class<?> requestedObject) throws NetworkTimeoutException {
		NetworkCheck check = new NetworkCheck(new ToleranceCheckTask() {
			public boolean check() {

				if (networkObject != null) {
					if (networkObject.getClass().equals(requestedObject)) {
						return true;
					}
				}
				return false;
			}
		});
		if (networkObject == null) {
			throw new NetworkTimeoutException();
		}
		if (!check.isCheckOk()) {
			throw new NetworkTimeoutException();
		}
		Object o = networkObject;
		networkObject = null;
		return o;
	}
	
	
}
