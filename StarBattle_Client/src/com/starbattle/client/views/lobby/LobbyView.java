package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.views.lobby.chat.ChatManager;
import com.starbattle.client.views.lobby.control.FriendActionReceiver;
import com.starbattle.client.views.lobby.control.FriendConnectionReceiver;
import com.starbattle.client.views.lobby.friends.FriendPanel;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_Logout;

public class LobbyView extends ContentView {

	public final static int VIEW_ID = 3;

	private FriendPanel friendPanel;
	private PicturePanel picturePanel;
	private LevelBarDisplay levelBarDisplay = new LevelBarDisplay();
	private MoneyDisplay moneyDisplay = new MoneyDisplay();
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
		startPanel = new StartPanelDisplay();

		JPanel topPanel = new JPanel(new BorderLayout());
		JButton logout = new DesignButton("Disconnect");
		JButton profile = new DesignButton("Profile");

		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel southWestPanel = new JPanel(new BorderLayout());

		
		view.setLayout(new BorderLayout());
		JPanel topLeft = new JPanel();
		topLeft.add(profile);
		topLeft.add(logout);
		topLeft.setOpaque(false);

		JPanel topRight = new JPanel();
		topRight.add(levelBarDisplay.getView());
		topRight.add(Box.createHorizontalStrut(20));
		topRight.add(moneyDisplay.getView());
		topRight.add(Box.createHorizontalStrut(10));
		topRight.setOpaque(false);

		topPanel.add(topRight, BorderLayout.EAST);
		topPanel.add(topLeft, BorderLayout.WEST);
		topPanel.setBackground(new Color(80, 80, 80));
		view.add(topPanel, BorderLayout.NORTH);

		centerPanel.add(friendPanel.getView(), BorderLayout.EAST);
		southWestPanel.add(picturePanel.getView(), BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(100, 100, 100));
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(startPanel.getView(), BorderLayout.CENTER);
		southWestPanel.add(bottomPanel, BorderLayout.SOUTH);
		centerPanel.add(southWestPanel, BorderLayout.CENTER);

		view.add(centerPanel, BorderLayout.CENTER);

		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				networkConnection.getSendConnection().sendTCP(new NP_Logout());
				openView(LoginView.VIEW_ID);
			}
		});
	}

	@Override
	protected void initView() {
		resizeWindow(windowSize);
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
