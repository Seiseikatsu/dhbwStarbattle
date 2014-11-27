package com.starbattle.client.main;

import java.io.IOException;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.NetworkConnectionListener;
import com.starbattle.client.main.error.ConnectionErrorListener;
import com.starbattle.client.resource.ClientConfiguration;
import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.views.error.ConnectionErrorView;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.friends.AddFriendView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.client.views.reset.ResetPasswordView;
import com.starbattle.client.window.GameWindow;

public class StarBattleClient {

	public static void main(String[] args) {
		new StarBattleClient();
	}

	private GameWindow window;
	private NetworkConnection connection;
	private boolean shutdown = false;

	public StarBattleClient() {
		initClient();
	}

	public void initClient() {
		GUIDesign.load();

		window = new GameWindow(null, "StarBattle Client");
		ClientConfiguration.loadConfiguration();

		// create network connection
		connection = new NetworkConnection(new NetworkConnectionHandler());

		// add connection error view
		window.addView(new ConnectionErrorView(new ConnectionErrorHandler()));
		try {

			connection.start("localhost", 56777, 56777);
			openWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (!shutdown) {
				e.printStackTrace();
				ConnectionErrorView.setErrorInfo(e);
				window.open(ConnectionErrorView.VIEW_ID);
			}
		}
	}

	public void shutdown() {
		shutdown = true;
		window.getWindow().dispose();
		connection.getClient().close();
	}

	private void openWindow() {
		// create views
		window.addView(new LoginView(connection));
		window.addView(new RegisterView(connection));
		window.addView(new ResetPasswordView(connection));
		window.addView(new LobbyView(connection));
		window.addView(new PlayView(connection));
		window.addView(new AddFriendView(connection));
		// open login window
		window.open(LoginView.VIEW_ID);
	}

	// reacts if connection to server opened/closed
	private class NetworkConnectionHandler implements NetworkConnectionListener {

		@Override
		public void onConnect() {
			System.out.println("Client connected to Server!");
		}

		@Override
		public void onDisconnect() {
			if (!shutdown) {
				window.open(ConnectionErrorView.VIEW_ID);
			}
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

	public NetworkConnection getConnection() {
		return connection;
	}

	public GameWindow getWindow() {
		return window;
	}

}
