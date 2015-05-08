package com.starbattle.network.connection.objects;


/**
 * 
 * This packet can be send from the server or the client:
 * 
 * Clients send this to inform the server they dont want to search for a game anymore
 *(to change setttings or doing something else)
 * 
 * The server can send this to clients to inform no match could be found (after long waiting maybe)
 * or if no gamemode-settings matched the values from the {@link NP_EnterMatchQueue} before.
 * 
 * @author Roland
 *
 */

public class NP_CancelMatchQueue {

}
