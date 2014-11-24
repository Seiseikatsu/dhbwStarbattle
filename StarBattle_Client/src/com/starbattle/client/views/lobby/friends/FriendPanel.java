package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.network.connection.objects.NP_LobbyFriends;

public class FriendPanel extends ViewModel{

	

	private String[] friendPanelNames={"Online","Offline","Friend Requests","Requests sent"};
	private String[] friendPanelIcons={"user.png","user_offline.png","user_add.png","user_go.png"};
	private FriendList[] friendPanels=new FriendList[4];
	private JButton addNew=new DesignButton("Add Friend", ResourceLoader.loadIcon("add.png"));
	private FriendActionListener friendActionListener;
	
	public FriendPanel(final LobbyView lobbyView, FriendActionListener friendActionListener)
	{
		this.friendActionListener=friendActionListener;
		initLayout();	

		addNew.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				lobbyView.openWindowView(AddFriendView.VIEW_ID);
			}
		});
	}
	
	public void update(NP_LobbyFriends friends)
	{
		
	}
	
	private void initLayout()
	{
		setBackgroundImage(ResourceLoader.loadImage("friendlistBackground.jpg"));
		view.setPreferredSize(new Dimension(300,0));
		view.setBorder(BorderFactory.createLineBorder(new Color(50,50,50),3));
		
		view.setLayout(new BorderLayout());
		JPanel content=new JPanel();
	    content.setOpaque(false);
		content.setLayout(new VerticalLayout());
		
		for(int i=0; i<friendPanels.length; i++)
		{
			friendPanels[i]=new FriendList(friendPanelNames[i],friendPanelIcons[i],friendActionListener);	
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
		header.add(addNew);
		
		
		
		// ADD test friends
		friendPanels[0].addRelation(new FriendRelation("Hans", 0, true));
		friendPanels[0].addRelation(new FriendRelation("Peter", 0, true));
		friendPanels[0].addRelation(new FriendRelation("Htetef", 0, true));
		friendPanels[0].addRelation(new FriendRelation("342sdsa", 0, true));
		friendPanels[0].addRelation(new FriendRelation("ogdioa2", 0, true));
		friendPanels[0].addRelation(new FriendRelation("scuion23##", 0, true));
		
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
