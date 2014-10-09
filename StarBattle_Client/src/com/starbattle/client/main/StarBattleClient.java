package com.starbattle.client.main;

import java.awt.Dimension;
import java.io.IOException;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.views.GameView;
import com.starbattle.client.views.LoginView;
import com.starbattle.client.views.RegisterView;
import com.starbattle.client.window.GameWindow;

public class StarBattleClient {

	public static void main(String[] args) {
		new StarBattleClient();
	}

	private GameWindow window;
	private NetworkConnection connection;

	public StarBattleClient() {
		Dimension size = new Dimension(0,0);
		window = new GameWindow(size, "StarBattle Client");
		// create network connection
		connection = new NetworkConnection();

		try {
			connection.start("localhost", 56777, 56777);
			openWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openWindow() {
		// create views
		window.addView(new LoginView(connection));
		window.addView(new RegisterView(connection));
		window.addView(new GameView(connection));
		// open login window
		window.open(LoginView.VIEW_ID);
	}
}
