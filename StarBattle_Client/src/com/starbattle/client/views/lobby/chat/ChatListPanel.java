package com.starbattle.client.views.lobby.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class ChatListPanel extends ViewModel{

	private ChatManager chatManager;
	private JPanel content=new JPanel();
	private ChatItemListener listener;
	
	public ChatListPanel(ChatManager chatManager, ChatItemListener chatItemListener)
	{
		this.listener=chatItemListener;
		this.chatManager=chatManager;
		initLayout();
	}
	
	private void initLayout()
	{
		content.setLayout(new VerticalLayout(2));
		view.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(content);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollBar bar = scrollPane.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(12, 0));
		bar.setBackground(new Color(50, 50, 50));
		view.add(scrollPane,BorderLayout.CENTER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		DesignLabel title=new DesignLabel("Open Chats",ResourceLoader.loadIcon("email.png"));
		title.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		title.setBackground(new Color(70,70,70));
		title.setOpaque(true);
		view.setOpaque(false);
		view.add(title,BorderLayout.NORTH);
		content.setOpaque(false);
	}
	
	public void update()
	{
		content.removeAll();
		for (ChatContainer chat : chatManager.getChats().values()) {
			ChatListItem item = new ChatListItem(chat.getFriendName(), listener);
			item.setName(chat.getFriendName());
			content.add(item);
		}
		content.revalidate();
		view.repaint();
	}
	
}
