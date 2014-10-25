package com.starbattle.client.views.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_Logout;

public class PlayView extends ContentView{
	public final static int VIEW_ID = 4;
	private Dimension windowSize=new Dimension(1000,600);
	
	public PlayView(final NetworkConnection networkConnection){
		JPanel bottomPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JButton play = new JButton("Play");
		JPanel blocker=new JPanel();
		JButton cancel = new JButton("Cancel");
		
		view.setLayout(new BorderLayout());
		
		blocker.setPreferredSize(new Dimension(150,150));
		blocker.setBackground(bottomPanel.getBackground());
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				openView(LobbyView.VIEW_ID);
			}
		});
		bottomPanel.add(cancel);
		bottomPanel.add(blocker);
		bottomPanel.add(play);
		view.add(bottomPanel, BorderLayout.SOUTH);
		
		centerPanel.setBackground(Color.LIGHT_GRAY);
		view.add(centerPanel, BorderLayout.CENTER);
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
