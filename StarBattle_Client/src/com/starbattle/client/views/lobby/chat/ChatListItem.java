package com.starbattle.client.views.lobby.chat;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		DesignButton open= new DesignButton(chatName,ResourceLoader.loadIcon("email.png"));
		open.setButtonStyle(1);
		DesignButton close = new DesignButton(ResourceLoader.loadIcon("cross.png"));
		add(close);
		add(open);
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
