package com.starbattle.client.main;

import java.awt.Dimension;
import java.io.IOException;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.NetworkConnectionListener;
import com.starbattle.client.main.error.BugSplashDialog;
import com.starbattle.client.main.error.ConnectionErrorListener;
import com.starbattle.client.resource.ClientConfiguration;
import com.starbattle.client.views.error.ConnectionErrorView;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.friends.AddFriendView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.views.profile.PlayerProfileView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.client.views.reset.ResetPasswordView;
import com.starbattle.client.views.settings.SettingsView;
import com.starbattle.client.views.shop.ShopView;
import com.starbattle.client.window.GameWindow;
import com.starbattle.client.window.LoadingWindow;
import com.starbattle.network.connection.NetworkRegister;

public class ClientFactory {

	private LoadingWindow loadingWindow;
	private GameWindow window;
	private NetworkConnection connection;
	private boolean shutdown = false;
	public static Dimension windowSize=new Dimension(1000,600);

	
	public ClientFactory() {
		
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
		
		loadingWindow.loadProgress();

		window = new GameWindow(null, "StarBattle Client");
		ClientConfiguration.loadConfiguration();
		loadingWindow.loadProgress();

		// create network connection
		loadingWindow.loadProgress();

		// add connection error view
		window.addView(new ConnectionErrorView(new ConnectionErrorHandler()));
		loadingWindow.loadProgress();
		try {

			connection.start("localhost", NetworkRegister.TCP_PORT,NetworkRegister.UDP_PORT);
			loadingWindow.loadProgress();
			openWindow();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (!shutdown) {
				e.printStackTrace();
				ConnectionErrorView.setErrorInfo(e);
				loadingWindow.close();
				window.open(ConnectionErrorView.VIEW_ID);
			}
		}
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
		window.addView(new SettingsView(connection));
		loadingWindow.loadProgress();
		window.addView(new ShopView(connection));
		loadingWindow.loadProgress();
		window.addView(new PlayerProfileView(connection));
		loadingWindow.loadProgress();
		// open login window
		window.open(LoginView.VIEW_ID);
		loadingWindow.loadProgress();
		loadingWindow.close();
	}

	
	private class NetworkConnectionHandler implements NetworkConnectionListener {

		@Override
		public void onConnect() {
			System.out.println("Client connected to Server!");
		}

		@Override
		public void onDisconnect(String cause) {
			if (!shutdown) {
				if(cause!=null)
				{
					//display disconnect cause
					BugSplashDialog.showMessage(cause);
				}
				loadingWindow.close();
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
}
