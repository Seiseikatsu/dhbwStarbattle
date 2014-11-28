package com.starbattle.client.views.profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.CustomPaintInterface;

public class PlayerProfileView extends ContentView {

	public final static int VIEW_ID = 7;
	private static ProfileViewData playerData;
	private DesignButton backButton =new DesignButton("Close");
	private DesignLabel title=new DesignLabel("",ResourceLoader.loadIcon("user_astronaut.png"),30);
	private ProfilePainter profilePainter=new ProfilePainter();
	
	public PlayerProfileView(NetworkConnection networkConnection) {
		windowSize = StarBattleClient.windowSize;
		 playerData=new ProfileViewData(); //default initialization
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout());
		title.setHorizontalAlignment(SwingConstants.LEFT);
		view.setBackgroundImage(ResourceLoader.loadImage("space_background.jpg"));
		JPanel topLane=new JPanel(new BorderLayout(50,0));
		topLane.add(backButton,BorderLayout.WEST);
		topLane.add(title,BorderLayout.CENTER);
		topLane.setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 1));
		topLane.setBackground(new Color(50,50,50));
		
		backButton.setButtonStyle(1);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openView(LobbyView.VIEW_ID);
			}
		});
		
		view.setCustomPaintInterface(new CustomPaintInterface() {
			
			@Override
			public void paintPanel(Graphics g, Dimension size) {
				int w=size.width;
				int h=size.height;
				g.setColor(new Color(150,150,150,150));
				g.fillRect(0,0,w,h);
				//paint profile
				profilePainter.paintProfile(g, playerData);
			}
		});
		
		view.add(topLane,BorderLayout.NORTH);
	}
	
	public static void setPlayerProfileData(ProfileViewData data)
	{
	  PlayerProfileView.playerData=data;
	}

	@Override
	protected void initView() {
		title.setText(playerData.getPlayerName());
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
