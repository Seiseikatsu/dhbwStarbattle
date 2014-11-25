package com.starbattle.client.testinterface.tester;

public class GUIElementNotFoundException extends Exception {

	public GUIElementNotFoundException(String elementName)
	{
		super("Failed to find GUI Element '"+elementName+"'");
	}
	
}
