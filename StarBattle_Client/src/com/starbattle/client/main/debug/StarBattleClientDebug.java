package com.starbattle.client.main.debug;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.resource.ClientConfiguration;
import com.starbattle.client.views.LobbyView;
import com.starbattle.client.views.LoginView;
import com.starbattle.client.views.RegisterView;
import com.starbattle.client.views.ResetPasswordView;
import com.starbattle.client.window.GameWindow;

public class StarBattleClientDebug {

	public static void main(String[] args) {
		
		ClientConfiguration.loadConfiguration();
		
		GameWindow window = new GameWindow(null, "Client DEBUG Modus");
		System.out.println("Client DEBUG Modus| Open Client in debug modus");
		NetworkConnection debugConnection=new DebugNetworkConnection(null);
		
		//add views to test
		window.addView(new LoginView(debugConnection));
		window.addView(new RegisterView(debugConnection));
		window.addView(new ResetPasswordView(debugConnection));
		window.addView(new LobbyView(debugConnection));
		
		//set starting view
		window.open(LobbyView.VIEW_ID);
		
	}
	
}
