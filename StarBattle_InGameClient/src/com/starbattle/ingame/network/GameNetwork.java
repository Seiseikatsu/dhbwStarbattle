package com.starbattle.ingame.network;

public class GameNetwork {

	private NetworkObjectResolver networkObjectResolver;
	private GameSendConnection sendConnection;
	private static long LAST_RECEIVE_TIME;
	private static long LAST_SEND_TIME;
	private static int ping;
	

	public GameNetwork(GameSendConnection send) {
		networkObjectResolver = new NetworkObjectResolver();
		this.sendConnection = send;
	}

	public void sendTCP(Object object) {
		sendConnection.sendTCP(object);
	}
	
	public void sendUDP(Object object) {
		sendConnection.sendUDP(object);
	}
	

	public void setReceiveListener(ObjectReceiveListener receive) {
		networkObjectResolver.setObjectReceiveListener(receive);
	}

	public void receiveObject(Object object) {
		long time=System.currentTimeMillis();
		ping=(int) (time-LAST_RECEIVE_TIME);
		LAST_RECEIVE_TIME=time;
		networkObjectResolver.resolveMessage(object);
	}

	public static int getPing()
	{
		return ping;
	}
}
