package com.starbattle.ingame.main;

public class GameClientException extends Exception{

	
	public GameClientException(String message) {
		super("Exception in InGameClient: "+message);
	}
	
}
