package com.starbattle.client.testAPI;

public class GUIElementNotFoundException extends Exception {

	public GUIElementNotFoundException(String elementName)
	{
		super("Failed to find GUI Element '"+elementName+"'");
	}
	
}
