package com.starbattle.client.views.play.queue;

import java.awt.BorderLayout;

import javax.swing.JButton;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.views.lobby.PicturePanel;
import com.starbattle.client.window.ContentView;

public class WaitingView extends ContentView{

	public final static int VIEW_ID = 5;

	
	private PicturePanel picturePanel=new PicturePanel();
	private NetworkConnection connection;
	private JButton cancel=new DesignButton("Cancel");
	
	public WaitingView(NetworkConnection connection) {
		this.connection=connection;
	}
	
	@Override
	protected void initView() {
		
		view.setLayout(new BorderLayout());
		view.add(picturePanel.getView(),BorderLayout.CENTER);
		view.add(cancel,BorderLayout.SOUTH);
		
	}

	@Override
	protected void onClosing() {
		
	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
