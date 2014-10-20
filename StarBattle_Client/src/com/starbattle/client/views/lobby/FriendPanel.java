package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.StandardViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class FriendPanel extends StandardViewModel{

	
	private JLabel friendsOnline=new JLabel("0");
	private JPanel content=new JPanel();
	
	public FriendPanel()
	{
		initLayout();
	
	}
	
	private void initLayout()
	{
		
		view.setPreferredSize(new Dimension(300,0));
		view.setBorder(BorderFactory.createEtchedBorder());
		
		view.setLayout(new BorderLayout());
		content.setLayout(new VerticalLayout());
		content.setBackground(new Color(0,100,130));
		JPanel header=new JPanel();
		view.add(header,BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(content);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		view.add(scrollPane,BorderLayout.CENTER);
		
		header.setBackground(new Color(150,210,240));
		JLabel title=new JLabel("Friends Online",ResourceLoader.loadIcon("user.png"),0);
		header.add(title);
		header.add(friendsOnline);	
		friendsOnline.setForeground(Color.WHITE);
	}
	
}
