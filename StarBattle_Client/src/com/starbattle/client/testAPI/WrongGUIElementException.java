package com.starbattle.client.testAPI;


public class WrongGUIElementException extends Exception{

	
	public WrongGUIElementException(Class<?> class1,Class <?> class2)
	{
		new Exception("Wrong GUI Element Exception: Expecteced '"+class2+"' but is '"+class1+"'");
	}
	
}
