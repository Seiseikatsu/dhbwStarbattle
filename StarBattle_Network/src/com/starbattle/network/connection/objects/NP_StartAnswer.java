package com.starbattle.network.connection.objects;

/**
 * Received by the server as an answer to a login or register attempt.
 * 
 * @author Roland
 *
 */
public class NP_StartAnswer {

	/**
	 * Tells whether the login was successful or not.
	 * 
	 */
	public boolean openGame;
	
	/**
	 * Error Message if something is not correct
	 */
	public String answerMessage;
	
}
