package com.starbattle.client.views.lobby.chat;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.resource.ResourceLoader;

public class ChatListItem extends JPanel {

	private ChatItemListener itemChatListener;
	private String chatName;

	public ChatListItem(String name, ChatItemListener removeChatListener) {
		this.chatName = name;
		this.itemChatListener = removeChatListener;
		initLayout();
	}

	private void initLayout() {
		this.setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 10));		
		JButton open= new DesignButton(chatName,ResourceLoader.loadIcon("email.png"));
		add(open);
		JButton close = new DesignButton(ResourceLoader.loadIcon("cross.png"));
		add(close);

		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChatListener.showChat(chatName);
			}
		});
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				itemChatListener.removeChat(chatName);
			}
		});
	}

}
