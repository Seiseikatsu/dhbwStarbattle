package com.starbattle.client.testinterface.exceptions;

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
