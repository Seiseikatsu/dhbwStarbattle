package com.starbattle.network.connection.objects;

/**
 * 
 * Sent by the client to inform the server he wants to play a
 * given game. the game will be filtered by the options below. 
 * 
 * null Strings or playerNumber = 0 will result in ignoring 
 * these values => send empty object will cause the player to 
 * be matched in a completely random setting of a game.
 * 
 * @author Roland
 *
 */

public class NP_EnterMatchQueue {

	private String modeName;
	private int numberOfPlayers;
	private String mapName;
	
	
}
