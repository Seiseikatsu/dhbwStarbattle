package com.starbattle.client.views.lobby.chat;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JPanel;

import com.starbattle.client.window.ContentPanel;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_ChatMessage;

public class ChatManager {

	private HashMap<String, ChatContainer> chats = new HashMap<String, ChatContainer>();
	private ContentPanel parentPanel;
	private SendServerConnection serverConnection;
	private JPanel chatListPanel=new JPanel();
	
	public ChatManager(SendServerConnection serverConnection,ContentPanel parent) {
		this.parentPanel = parent;
		this.serverConnection=serverConnection;
		chatListPanel.setOpaque(false);
		chatListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		chatListPanel.setPreferredSize(new Dimension(0,30));
	}

	
	private void updateChatListlayout()
	{
		chatListPanel.removeAll();
		ChatItemListener removeChatListener=new RemoveListener();
		for(String chatName: chats.keySet())
		{
			ChatListItem chat=new ChatListItem(chatName, removeChatListener);
			chatListPanel.add(chat);
		}
		chatListPanel.revalidate();
		chatListPanel.repaint();
	}
	
	public void closeChat(String to)
	{
		if(chats.containsKey(to))
		{
			chats.get(to).forceClose();
			chats.remove(to);
			updateChatListlayout();
		}
	}
	
	
	
	public void openChat(String to) {
		if (!chats.containsKey(to)) {
			// Create new chat window
			ChatContainer chat = new ChatContainer(to, parentPanel, new WriteListener());
			chats.put(to, chat);
			updateChatListlayout();
		} else {
			// Reopen closed windows
			chats.get(to).forceOpen();
		}	
	}
	
	public JPanel getChatListPanel() {
		return chatListPanel;
	}

	public void receiveMessage(NP_ChatMessage message) {
		String from = message.name;
		if (!chats.containsKey(from)) {
			// create new chat window
			openChat(from);
		}
		// send message to window
		chats.get(from).receiveMessage(message.message);
	}

	private class RemoveListener implements ChatItemListener{

		@Override
		public void removeChat(String name) {
			closeChat(name);
		}

		@Override
		public void showChat(String name) {
			openChat(name);
		}
		
	}
	
	private class WriteListener implements WriteMessageListener {

		@Override
		public void writeMessage(String to, String text) {
			
			//Send Chat Message
			NP_ChatMessage message=new NP_ChatMessage();
			message.name=to;
			message.message=text;
			serverConnection.sendTCP(message);
		}

	}

}
