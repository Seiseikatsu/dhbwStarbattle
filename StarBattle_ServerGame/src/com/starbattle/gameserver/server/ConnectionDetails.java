package com.starbattle.gameserver.server;

public class ConnectionDetails {

	private int tcp_port;
	private int upd_port;
	
	public ConnectionDetails(int tcp, int udp)
	{
		this.tcp_port=tcp;
		this.upd_port=udp;
	}
	
	public int getTcp_port() {
		return tcp_port;
	}
	
	public int getUpd_port() {
		return upd_port;
	}
}
