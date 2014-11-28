package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.views.lobby.chat.ChatManager;
import com.starbattle.client.views.lobby.control.FriendActionReceiver;
import com.starbattle.client.views.lobby.control.FriendConnectionReceiver;
import com.starbattle.client.views.lobby.control.PlayerBarListener;
import com.starbattle.client.views.lobby.control.StartPanelListener;
import com.starbattle.client.views.lobby.friends.FriendPanel;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_Logout;

public class LobbyView extends ContentView {

	public final static int VIEW_ID = 3;

	private FriendPanel friendPanel;
	private PicturePanel picturePanel;

	private PlayerBarDisplay playerProfileDisplay;
	private StartPanelDisplay startPanel;
	private NetworkConnection networkConnection;
	private ChatManager chatManager;
	private FriendActionReceiver friendActionReceiver;

	public LobbyView(final NetworkConnection networkConnection) {
		this.networkConnection = networkConnection;
		windowSize = new Dimension(1000, 600);
		chatManager = new ChatManager(networkConnection.getSendConnection(), this.view);
		friendActionReceiver = new FriendActionReceiver(chatManager, networkConnection.getSendConnection());
		initLayout();
		FriendConnectionReceiver friendConnectionListener = new FriendConnectionReceiver(chatManager, friendPanel);
		networkConnection.setFriendListener(friendConnectionListener);
	}

	private void initLayout() {
		friendPanel = new FriendPanel(this, chatManager, friendActionReceiver);
		picturePanel = new PicturePanel();
		startPanel = new StartPanelDisplay(new StartPanelListener() {		
			@Override
			public void startGame() {
				openView(PlayView.VIEW_ID);
			}			
			@Override
			public void openShop() {
				
			}			
			@Override
			public void openSettings() {
				
			}			
			@Override
			public void disconnect() {
				NP_Logout logout=new NP_Logout();
				networkConnection.getSendConnection().sendTCP(logout);
				openView(LoginView.VIEW_ID);
			}
		});
		playerProfileDisplay = new PlayerBarDisplay(new PlayerBarListener() {			
			@Override
			public void showPlayerProfile() {
				
			}
		});

		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel southWestPanel = new JPanel(new BorderLayout());
		view.setLayout(new BorderLayout());
		view.add(playerProfileDisplay.getView(), BorderLayout.NORTH);
		centerPanel.add(friendPanel.getView(), BorderLayout.EAST);
		southWestPanel.add(picturePanel.getView(), BorderLayout.CENTER);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(100, 100, 100));
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(startPanel.getView(), BorderLayout.CENTER);
		southWestPanel.add(bottomPanel, BorderLayout.SOUTH);
		centerPanel.add(southWestPanel, BorderLayout.CENTER);
		view.add(centerPanel, BorderLayout.CENTER);

	}

	@Override
	protected void initView() {
		resizeWindow(windowSize);
		playerProfileDisplay.update(); // update player bar display
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

	public FriendPanel getFriendPanel() {
		return friendPanel;
	}

	public ChatManager getChatManager() {
		return chatManager;
	}

}
