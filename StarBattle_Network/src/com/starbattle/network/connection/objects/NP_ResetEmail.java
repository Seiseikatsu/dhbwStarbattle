package com.starbattle.network.connection.objects;

/**
 * Sent by the client to inform the server you want to reset your email.
 * This will only work if your not logged in. 
 * You have to pass a valid Accountname - Email Combination.
 * 
 * TODO: create answer protocol from the server if an email was sent! 
 * 
 * @author Roland
 *
 */
public class NP_ResetEmail {

	/**
	 * Name of your account
	 */
	public String name;
	
	/**
	 * Your email address
	 */
	public String email;
	
}
