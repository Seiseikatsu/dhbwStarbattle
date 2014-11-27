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
import com.starbattle.client.window.LoadingWindow;

public class StarBattleClient {

	public static void main(String[] args) {
		new StarBattleClient();
	}

	private GameWindow window;
	private NetworkConnection connection;
	private LoadingWindow loadingWindow;
	private boolean shutdown = false;

	public StarBattleClient() {
		initClient();
	}
	

	public void initClient() {

		// Create Loading Window
		loadingWindow = new LoadingWindow();
		loadingWindow.setMaxProgress(11);// max progress count
		Thread loadingWindowThread=new Thread(new Runnable() {		
			@Override
			public void run() {
				loadingWindow.open();
			}
		});
		loadingWindowThread.start();

		//init client
		GUIDesign.load();
		loadingWindow.loadProgress();

		window = new GameWindow(null, "StarBattle Client");
		ClientConfiguration.loadConfiguration();
		loadingWindow.loadProgress();

		// create network connection
		connection = new NetworkConnection(new NetworkConnectionHandler());
		loadingWindow.loadProgress();

		// add connection error view
		window.addView(new ConnectionErrorView(new ConnectionErrorHandler()));
		loadingWindow.loadProgress();
		try {

			connection.start("localhost", 56777, 56777);
			loadingWindow.loadProgress();
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
		loadingWindow.loadProgress();
		window.addView(new RegisterView(connection));
		loadingWindow.loadProgress();
		window.addView(new ResetPasswordView(connection));
		loadingWindow.loadProgress();
		window.addView(new LobbyView(connection));
		loadingWindow.loadProgress();
		window.addView(new PlayView(connection));
		loadingWindow.loadProgress();
		window.addView(new AddFriendView(connection));
		loadingWindow.loadProgress();
		// open login window
		window.open(LoginView.VIEW_ID);
		loadingWindow.loadProgress();
		loadingWindow.close();
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
