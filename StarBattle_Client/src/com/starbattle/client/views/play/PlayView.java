package com.starbattle.client.views.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkGameQueryListener;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.play.queue.WaitingView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_CancelMatchQueue;
import com.starbattle.network.connection.objects.NP_EnterMatchQueue;
import com.starbattle.network.connection.objects.NP_GameModesList;
import com.starbattle.network.connection.objects.NP_RequestGameModes;

public class PlayView extends ContentView {
	public final static int VIEW_ID = 4;

	private GameModeDisplay gameModeDisplay = new GameModeDisplay();

	private NetworkConnection networkConnection;
	private DesignButton playButton = new DesignButton(PLAY);
	private DesignButton cancelButton = new DesignButton("Back");

	public final static String PLAY = "Play";
	public final static String CANCEL = "Cancel";


	public PlayView(final NetworkConnection networkConnection) {
		windowSize = StarBattleClient.windowSize;
		this.networkConnection = networkConnection;
		networkConnection.setGameQueryListener(new QueryListener());
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 20));
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(100, 100, 100));
		topPanel.add(new DesignLabel("Game Settings", 30));
		playButton.setFontSize(25f);
		playButton.setButtonStyle(1);
		cancelButton.setButtonStyle(1);
		cancelButton.setFontSize(25f);
		view.setLayout(new BorderLayout());

		playButton.setEnabled(true);

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				openView(LobbyView.VIEW_ID);
			}
		});

		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

					enterQuery();
			}
		});

		bottomPanel.setBackground(new Color(100, 100, 100));
		bottomPanel.add(cancelButton);
		bottomPanel.add(playButton);
		view.add(bottomPanel, BorderLayout.SOUTH);
		view.add(topPanel, BorderLayout.NORTH);
		view.add(gameModeDisplay.getView(), BorderLayout.CENTER);

	}


	private void enterQuery() {
		openView(WaitingView.VIEW_ID);
		
		NP_EnterMatchQueue enter = new NP_EnterMatchQueue();
		enter.modeName = gameModeDisplay.getSelectedMode();
		enter.mapName=gameModeDisplay.getSelectedMap();
		networkConnection.getSendConnection().sendTCP(enter);
	}

	@Override
	protected void initView() {
		// request game mode list
		NP_RequestGameModes request = new NP_RequestGameModes();
		networkConnection.getSendConnection().sendTCP(request);
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {

		return VIEW_ID;
	}

	private class QueryListener implements NetworkGameQueryListener {

		@Override
		public void receivedGameModes(NP_GameModesList modes) {

			gameModeDisplay.initGameModes(modes);

		}

		@Override
		public void receivedQueryCancel() {
			openView(VIEW_ID);	
		}

	}
}
