package com.starbattle.client.testAPI;

public class LoginFailureException extends Exception {

	
	public LoginFailureException()
	{
		super("Failed to Login: Network Timeout!");
	}
	
	public LoginFailureException(String cause)
	{
		super("Failed to Login: "+cause);
	}
	
}
