package com.starbattle.client.testinterface.exceptions;

import com.starbattle.client.testinterface.main.ClientTestInterface;

public class NetworkTimeoutException extends Exception{

	public NetworkTimeoutException()
	{
		super("Network Object Receive Timeout (after "+ClientTestInterface.networkTimeout+" seconds)");
	}
}
