package com.starbattle.network.connection.objects;

/**
 * 
 * Will be sent by the server to inform clients of the 
 * relation and online status of friends. 
 * 
 * @author Roland
 *
 */

public class NP_FriendUpdate {

	/**
	 * Username of the friend to be updated in your view
	 */
	public String name;
	/**
	 * What kind of update 
	 * @see NP_Constants 
	 */
	public int updateType;
	/**
	 * Is the target player online or not
	 */
	public boolean online;
	
}
