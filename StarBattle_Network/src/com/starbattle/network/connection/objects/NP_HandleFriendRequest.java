package com.starbattle.network.connection.objects;

/**
 * 
 * Protocol to inform the server about a friend request decision you have made.
 * Also used to delete existing relations to friends.
 * 
 * @author Roland
 *
 */

public class NP_HandleFriendRequest {

	/**
	 * Username of the friend request to handle
	 */
	public String friendName;
	/**
	 * <pre>
	 * On true: Accepts the request of a friend
	 * On false: Dont accept request and/or remove exisiting friend with that name
	 * </pre>
	 */
	public boolean accept;
	
}
