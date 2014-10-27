package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.views.lobby.friends.FriendPanel;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_Logout;
import com.starbattle.network.server.SendClientConnection;

public class LobbyView extends ContentView {

	public final static int VIEW_ID = 3;

	private FriendPanel friendPanel;
	private PicturePanel picturePanel;
	private LevelBarDisplay levelBarDisplay = new LevelBarDisplay();
	private MoneyDisplay moneyDisplay = new MoneyDisplay();
	private JButton playButton = new DesignButton("Play");
	private JButton shopButton = new DesignButton("Shop");
	private SendServerConnection sendConnection;

	public LobbyView(final NetworkConnection networkConnection) {
		sendConnection = networkConnection.getSendConnection();
		windowSize = new Dimension(1000, 600);
		initLayout();
	}

	private void initLayout() {
		friendPanel = new FriendPanel(this);
		picturePanel = new PicturePanel();

		JPanel topPanel = new JPanel(new BorderLayout());
		JButton logout = new DesignButton("Disconnect");
		JButton profile = new DesignButton("Profile");

		JPanel blocker = new JPanel();
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel southWestPanel = new JPanel(new BorderLayout());

		JPanel startPanel = new JPanel();

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
		startPanel.setBackground(Color.gray);
		startPanel.setPreferredSize(new Dimension(0, 200));

		shopButton.setPreferredSize(new Dimension(140, 100));
		startPanel.add(shopButton);
		blocker.setPreferredSize(new Dimension(150, 150));
		blocker.setBackground(startPanel.getBackground());
		startPanel.add(blocker);
		playButton.setPreferredSize(new Dimension(240, 180));
		startPanel.add(playButton);
		southWestPanel.add(startPanel, BorderLayout.SOUTH);
		centerPanel.add(southWestPanel, BorderLayout.CENTER);

		view.add(centerPanel, BorderLayout.CENTER);

		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendConnection.sendTCP(new NP_Logout());
				openView(LoginView.VIEW_ID);
			}
		});

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(PlayView.VIEW_ID);
			}
		});
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		resizeWindow(windowSize);
	}

	@Override
	protected void onClosing() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getViewID() {
		// TODO Auto-generated method stub
		return VIEW_ID;
	}

}
