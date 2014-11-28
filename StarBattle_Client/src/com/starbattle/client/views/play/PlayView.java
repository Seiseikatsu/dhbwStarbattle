package com.starbattle.client.views.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.window.ContentView;

public class PlayView extends ContentView{
	public final static int VIEW_ID = 4;
	private GameSettingsDisplay gameSettingsDisplay=new GameSettingsDisplay();
	
	public PlayView(final NetworkConnection networkConnection){
		windowSize = StarBattleClient.windowSize;
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 20));
		JPanel topPanel=new JPanel();
		topPanel.setBackground(new Color(100,100,100));
		topPanel.add(new DesignLabel("Game Settings", 30));
		DesignButton play = new DesignButton("Play");
		play.setFontSize(25f);
		play.setButtonStyle(1);
		DesignButton cancel = new DesignButton("Cancel");
		cancel.setButtonStyle(1);
		cancel.setFontSize(25f);
		view.setLayout(new BorderLayout());
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				openView(LobbyView.VIEW_ID);
			}
		});
		
		
		bottomPanel.setBackground(new Color(100,100,100));
		bottomPanel.add(cancel);
		bottomPanel.add(play);
		view.add(bottomPanel, BorderLayout.SOUTH);
		view.add(topPanel,BorderLayout.NORTH);
		view.add(gameSettingsDisplay.getView(), BorderLayout.CENTER);
		
	}
	
	@Override
	protected void initView() {
		
	}

	@Override
	protected void onClosing() {
		
	}

	@Override
	public int getViewID() {

		return VIEW_ID;
	}

}
