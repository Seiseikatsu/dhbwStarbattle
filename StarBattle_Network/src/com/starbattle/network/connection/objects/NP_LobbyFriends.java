package com.starbattle.network.connection.objects;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.network.connection.objects.constant.NP_Constants;

/**
 * Sent by the server to a client after login. Informs the client about all friend relations to display
 * them in the gui. 
 * 
 * @author Roland
 *
 */
public class NP_LobbyFriends {

	/**
	 * List of the friends usernames
	 */
	public List<String> friendNames=new ArrayList<String>();
	/**
	 * List of the relationState information
	 * @see NP_Constants 
	 */
	public List<Byte> relationStates=new ArrayList<Byte>();
	/**
	 * List of the online status: online on true, and offline on false
	 */
	public List<Boolean> friendOnline=new ArrayList<Boolean>();
	
}
