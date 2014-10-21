package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.client.layout.StandardViewModel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.resource.ResourceLoader;

public class FriendPanel extends StandardViewModel{

	

	private String[] friendPanelNames={"Online","Offline","Friend Requests","Request sent"};
	private String[] friendPanelIcons={"user.png","user_offline.png","user_add.png","user_go.png"};
	private FriendList[] friendPanels=new FriendList[4];
	
	public FriendPanel()
	{
		initLayout();	
	}
	
	private void initLayout()
	{
		
		view.setPreferredSize(new Dimension(300,0));
		view.setBorder(BorderFactory.createEtchedBorder());
		
		view.setLayout(new BorderLayout());
		JPanel content=new JPanel();
		content.setLayout(new VerticalLayout());
		//content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS)); 
		content.setBackground(new Color(0,100,130));
		
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
		
		view.add(scrollPane,BorderLayout.CENTER);
		
		header.setBackground(new Color(150,210,240));
		JLabel title=new JLabel("Friends",ResourceLoader.loadIcon("user.png"),0);
		header.add(title);
		
		
		// ADD test friends
		friendPanels[0].addRelation(new FriendRelation("Hans", 0, true));
		friendPanels[0].addRelation(new FriendRelation("Peter", 0, true));
		friendPanels[1].addRelation(new FriendRelation("Dieter", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Olaf heinz", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Max der Coole", 0, false));
		friendPanels[1].addRelation(new FriendRelation("Hossa", 0, false));
		friendPanels[2].addRelation(new FriendRelation("Jürgen Jürgens", 1, false));
		friendPanels[3].addRelation(new FriendRelation("Peter Peternsens", 2, false));
	
	}
	
	
}
