package com.starbattle.client.main;

import java.io.IOException;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.NetworkConnectionListener;
import com.starbattle.client.main.error.ConnectionErrorListener;
import com.starbattle.client.views.ConnectionErrorView;
import com.starbattle.client.views.GameView;
import com.starbattle.client.views.LoginView;
import com.starbattle.client.views.RegisterView;
import com.starbattle.client.views.ResetPasswordView;
import com.starbattle.client.window.GameWindow;

public class StarBattleClient {

	public static void main(String[] args) {
		new StarBattleClient();
	}

	private GameWindow window;
	private NetworkConnection connection;

	public StarBattleClient() {
		initClient();
	}

	public void initClient() {
		window = new GameWindow(null, "StarBattle Client");
		// create network connection
		connection = new NetworkConnection(new NetworkConnectionHandler());

		// add error view
		window.addView(new ConnectionErrorView(new ConnectionErrorHandler()));
		try {
			connection.start("localhost", 56777, 56777);
			openWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			window.open(ConnectionErrorView.VIEW_ID);
		}
	}

	private void openWindow() {
		// create views
		window.addView(new LoginView(connection));
		window.addView(new RegisterView(connection));
		window.addView(new ResetPasswordView(connection));
		window.addView(new GameView(connection));
		// open login window
		window.open(GameView.VIEW_ID);
	}

	// reacts if connection to server opened/closed
	private class NetworkConnectionHandler implements NetworkConnectionListener {

		@Override
		public void onConnect() {
			System.out.println("Client connected to Server!");
		}

		@Override
		public void onDisconnect() {
			window.open(ConnectionErrorView.VIEW_ID);
		}

	}

	// reacts to options in connection error view user inputs
	private class ConnectionErrorHandler implements ConnectionErrorListener {

		@Override
		public void tryReconnect() {
			// open everything agian
			window.close();
			initClient();
		}

		@Override
		public void exit() {
			// close
			window.close();
			System.exit(0);
		}

	}
}
