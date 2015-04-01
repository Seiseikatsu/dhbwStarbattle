package com.starbattle.network.connection.objects;

/**
 * Sent by the server to all connected players to inform about a short
 * downtime to do updates. The client will then close his connection.
 * 
 * @author Roland
 *
 */
public class NP_ServerStop {

	/**
	 * Reason of the server shutdown/update
	 */
	public String shutdown_Message;
	
}
