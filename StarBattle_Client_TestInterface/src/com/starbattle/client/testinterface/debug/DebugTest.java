package com.starbattle.client.testinterface.debug;

import com.starbattle.client.testinterface.exceptions.GUIElementNotFoundException;
import com.starbattle.client.testinterface.exceptions.WrongGUIElementException;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;

public class DebugTest {

	
	public static void main(String[] args)  {
		
		
		ClientAutomate client=ClientTestInterface.createNewTestClient();
		
		try {
			client.clickButton("Button_Register");
		
		} catch (GUIElementNotFoundException | WrongGUIElementException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		client.shutdown();
	}
	
}
