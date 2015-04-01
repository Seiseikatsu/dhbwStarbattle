package com.starbattle.network.connection.objects;

/**
 * 
 * 
 * Information about the battle result,
 * to display in game clients.
 * 
 * @author Roland
 *
 */

public class NP_BattleResults {

	/**
	 * Points of every player
	 */
	public int[] points;
	/**
	 * Team IDs of every player
	 */
	public int[] teams;
	/**
	 * Names of every player
	 */
	public String[] names;
	/**
	 * ID of the winner (matches playerID or in teammode the teamID)
	 */
	public int winnerID;
	/**
	 * Was the game a team game?
	 */
	public boolean teamGame;
	/**
	 * ID of the target player of this message
	 */
	public int playerID;
	/**
	 * Earned money from this game
	 */
	public int earnedMoney;
	/**
	 * Earned exp from this game
	 */
	public int earnedEXP;
	
}
