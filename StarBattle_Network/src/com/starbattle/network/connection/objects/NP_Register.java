package com.starbattle.network.connection.objects;

/**
 * Registration-formular to register a new account for the game.
 * This object can contain wrong or invalid data, the server will
 * check your information after.
 * 
 * @author Roland
 *
 */
public class NP_Register {

	/**
	 * The accountName  
	 */
	public String accountName;
	/**
	 * The displayName
	 */
	public String displayName;
	/**
	 * The password
	 */
	public String password;
	/**
	 * The email
	 */
	public String email;
	
	
}
