package com.starbattle.client.testinterface.exceptions;

import com.starbattle.client.testinterface.tester.ClientAutomate;

public class NetworkTimeoutException extends Exception{

	public NetworkTimeoutException()
	{
		super("Network Object Receive Timeout (after "+ClientAutomate.networkTimeout+" seconds)");
	}
}
