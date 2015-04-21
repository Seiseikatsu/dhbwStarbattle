package com.starbattle.ingame.network;

public class GameNetwork {

	private NetworkObjectResolver networkObjectResolver;
	private GameSendConnection sendConnection;

	public GameNetwork(GameSendConnection send) {
		networkObjectResolver = new NetworkObjectResolver();
		this.sendConnection = send;
	}

	public void sendToServer(Object object) {
		sendConnection.send(object);
	}

	public void setReceiveListener(ObjectReceiveListener receive) {
		networkObjectResolver.setObjectReceiveListener(receive);
	}

	public void receiveObject(Object object) {
		networkObjectResolver.resolveMessage(object);
	}

}
