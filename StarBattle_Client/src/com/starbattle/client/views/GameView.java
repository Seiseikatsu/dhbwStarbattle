package com.starbattle.client.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_Logout;

public class GameView extends ContentView{

	public final static int VIEW_ID = 3;
	private Dimension windowSize=new Dimension(1000,600);
	
	
	public GameView(final NetworkConnection networkConnection)
	{
		
		JButton logout=new JButton("Disconnect");
		view.setLayout(new FlowLayout());
		view.add(logout);
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				networkConnection.getSendConnection().sendTCP(new NP_Logout());
				openView(LoginView.VIEW_ID);
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
