package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.layout.StandardViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class FriendRelationView extends StandardViewModel{

	
	private static Icon deleteIcon=ResourceLoader.loadIcon("cross.png",12,12);
	private static Icon acceptIcon=ResourceLoader.loadIcon("accept.png",12,12);
	private static Icon mailIcon=ResourceLoader.loadIcon("email.png",12,12);
	
	private JLabel name=new JLabel();
	private JButton close=createButton(deleteIcon);
	private JPanel actions=new JPanel();
	
	public FriendRelationView(FriendRelation friend)
	{
		name.setText("  "+friend.getName());
		initLayout();
		switch(friend.getState())
		{
		case FriendRelation.RELATION_FRIENDS:  initFriendLayout(friend.isOnline()); break;
		case FriendRelation.RELATION_REQUEST:  initRequestLayout(); break;
		case FriendRelation.RELATION_PENDING:  initPendingLayout(); break;
		}
	}
	
	private void initLayout()
	{
		view.setPreferredSize(new Dimension(280,30));
		close.setIcon(deleteIcon);		
		view.setLayout(new BorderLayout());
		view.add(name,BorderLayout.WEST);
		view.add(actions,BorderLayout.EAST);
		name.setFont(name.getFont().deriveFont(12f));
		name.setForeground(new Color(50,50,50));
		view.setBorder(BorderFactory.createEtchedBorder());
		view.setBackground(new Color(180,230,255));
	}
	
	private void initFriendLayout(boolean online)
	{
	
		JButton write=createButton(mailIcon);
		if(online)
		{
			actions.add(write);		
		}
		actions.add(close);
	}
	
	private void initRequestLayout()
	{
		JButton write=createButton(acceptIcon);
		actions.add(write);
		actions.add(close);
	}
	
	private void initPendingLayout()
	{
		
		actions.add(close);
	}
	
	private JButton createButton(Icon icon)
	{
		JButton b=new JButton(icon);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setPreferredSize(new Dimension(12,12));
		return b;
	}
	
}
