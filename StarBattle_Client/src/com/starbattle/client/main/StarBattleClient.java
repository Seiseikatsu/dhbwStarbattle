package com.starbattle.client.main;

import java.awt.Dimension;

import com.starbattle.client.views.LoginView;
import com.starbattle.client.views.RegisterView;
import com.starbattle.client.window.GameWindow;

public class StarBattleClient {

	
	public static void main(String[] args) {
		new StarBattleClient();
	}
	
	public StarBattleClient()
	{
		Dimension size=new Dimension(400,400);
		GameWindow window=new GameWindow(size,"StarBattle Client");		
		//create views
		window.addView(new LoginView());
		window.addView(new RegisterView());
		//open login window
		window.open(LoginView.VIEW_ID);
	}
}
