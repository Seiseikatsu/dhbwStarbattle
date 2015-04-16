package com.starbattle.ingame.resource.player;

public class ResourceException extends Exception {

	
	public ResourceException(String cause) {
		super("ResourceExcpetion occured: "+cause);
	}
	
}
