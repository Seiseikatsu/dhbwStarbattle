package com.starbattle.client.views.lobby.chat;

import java.awt.Color;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;

public class ChatListPopupMenu extends JPopupMenu{

	
	public ChatListPopupMenu(Collection<ChatContainer> collection, ChatItemListener listener)
	{
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
		DesignLabel label=new DesignLabel(" Your Chats ",new Color(50,50,50));
		label.setOpaque(true);
		label.setBackground(new Color(200,200,200));
		add(label);
		
		add(Box.createVerticalStrut(5));
		
		this.setBackground(new Color(150,150,150));
		this.setLayout(new VerticalLayout());
		boolean hasChats=false;
		
		for(ChatContainer chat: collection)
		{
			ChatListItem item=new ChatListItem(chat.getFriendName(), listener);
			add(item);
			hasChats=true;
		}	
		
		if(!hasChats)
		{
			add(new DesignLabel("No Chats open",12));
		}
		
		this.revalidate();

	}
	
	
	
	
}
