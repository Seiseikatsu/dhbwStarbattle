package com.starbattle.network.connection.objects;

/**
 * Answer to a ChatMessage, if the receiver is offline
 * or other errors occured.
 * 
 * @author Roland
 *
 */
public class NP_ChatException {

	/**
	 * The username of the chat-target, in which the error occured 
	 */
	public String chatName;
	
}
