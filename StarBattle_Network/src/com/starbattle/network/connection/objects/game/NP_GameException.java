package com.starbattle.network.connection.objects.game;


/**
 * 
 * Sent by the server to clients in following scenarios:
 * 
 * 1. Server game crashed (exception etc.)
 * 2. Player sent a game-update to a nonexsitent game (game is over and player didnt notice or was disconnected)
 * 
 * Reaction from the client should be:
 * Close the IngameClient and open the client, 
 * without showing a result screen
 * 
 * 
 * @author Roland
 *
 */

public class NP_GameException {

}
