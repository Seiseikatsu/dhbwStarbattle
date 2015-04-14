package com.starbattle.network.connection.objects;

import java.util.List;

/**
 * 
 * Information about the currently open game modes to select.
 * 
 * @author Roland
 *
 */

public class NP_GameModesList {

	/**
	 * Mapnames (must match the map files) for every item
	 */
	public List<String> mapName;
	/**
	 * The number of players for the game
	 */
	public List<Integer> numOfPlayers;
	/**
	 * The names of the modes (Deathmatch etc).
	 * These name wont be indexed to a special enum,
	 * its only for display purpose in the client.
	 * Only the server knows the "real" existing modes.  
	 */
	public List<String> modeName;

}
