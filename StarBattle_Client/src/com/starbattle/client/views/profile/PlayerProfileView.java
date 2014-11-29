package com.starbattle.client.views.profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.DesignTextField;
import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.CustomPaintInterface;

public class PlayerProfileView extends ContentView {

	public final static int VIEW_ID = 7;
	private ProfileViewData playerData;
	private DesignButton backButton =new DesignButton("Close");
	private DesignButton searchButton =new DesignButton("Search Player");
	private JTextField searchField;
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
		
		JPanel botLane=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botLane.setBackground(new Color(50,50,50));
		botLane.setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 1));
		
		backButton.setButtonStyle(1);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openView(LobbyView.VIEW_ID);
			}
		});
		
		
		ActionListener searchAction=new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPlayer();
			}
		};
		
		searchField=new DesignTextField(20,searchAction);	
		searchButton.addActionListener(searchAction);
		
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
		
		
		botLane.add(searchField);
		botLane.add(searchButton);
		view.add(botLane,BorderLayout.SOUTH);
		view.add(topLane,BorderLayout.NORTH);
	}

	
	private void searchPlayer()
	{
		
	}
	
	@Override
	protected void initView() {
		//reset data and wait for network receive
		playerData=new ProfileViewData();
		title.setText("Loading Player Data...");
		view.repaint();
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
