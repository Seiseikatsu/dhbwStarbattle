package com.starbattle.client.testinterface.exceptions;


public class WrongGUIElementException extends Exception{

	
	public WrongGUIElementException(Class<?> class1,Class <?> class2)
	{
		super("Wrong GUI Element Exception: Expecteced '"+class2+"' but is '"+class1+"'");
	}
	
}
