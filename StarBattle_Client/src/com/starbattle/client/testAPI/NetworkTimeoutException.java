package com.starbattle.client.testAPI;

public class NetworkTimeoutException extends Exception{

	public NetworkTimeoutException()
	{
		super("Network Object Receive Timeout (after "+ClientAutomate.networkTimeout+" seconds)");
	}
}
