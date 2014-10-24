package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.client.layout.BackgroundViewModel;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.resource.ResourceLoader;

public class FriendPanel extends BackgroundViewModel{

	

	private String[] friendPanelNames={"Online","Offline","Friend Requests","Requests sent"};
	private String[] friendPanelIcons={"user.png","user_offline.png","user_add.png","user_go.png"};
	private FriendList[] friendPanels=new FriendList[4];
	
	public FriendPanel()
	{
		initLayout();	
	}
	
	private void initLayout()
	{
		view.setBackgroundImage(ResourceLoader.loadImage("friendlistBackground.jpg"));
		view.setPreferredSize(new Dimension(300,0));
		view.setBorder(BorderFactory.createLineBorder(new Color(50,50,50),3));
		
		view.setLayout(new BorderLayout());
		JPanel content=new JPanel();
	    content.setOpaque(false);
		content.setLayout(new VerticalLayout());
		
		for(int i=0; i<friendPanels.length; i++)
		{
			friendPanels[i]=new FriendList(friendPanelNames[i],friendPanelIcons[i]);	
			content.add(friendPanels[i].getView());
		}
		
		JPanel header=new JPanel();
		view.add(header,BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(content);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		JScrollBar bar=scrollPane.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(12,0));
		bar.setBackground(new Color(50,50,50));
		
	
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		view.add(scrollPane,BorderLayout.CENTER);
		
		header.setBackground(new Color(100,100,100));
		JLabel title=new DesignLabel("Friends","user.png");
		header.add(title);
		
		
		// ADD test friends
		friendPanels[0].addRelation(new FriendRelation("Hans", 0, true));
		friendPanels[0].addRelation(new FriendRelation("Peter", 0, true));
		friendPanels[1].addRelation(new FriendRelation("Dieter", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Olaf heinz", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Max der Coole", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Hossa", 0, false));
		friendPanels[2].addRelation(new FriendRelation("Jürgen Jürgens", 1, false));
		for(int i=0; i<30; i++)
		{
		friendPanels[3].addRelation(new FriendRelation("Peter Peternsens "+i, 2, false));
		}
	}
	
	
}
