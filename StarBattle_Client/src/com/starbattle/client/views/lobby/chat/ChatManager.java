package com.starbattle.client.views.lobby.chat;

import java.awt.Component;
import java.util.HashMap;

import com.starbattle.client.window.ContentPanel;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_ChatMessage;

public class ChatManager {

	private HashMap<String, ChatContainer> chats = new HashMap<String, ChatContainer>();
	private ContentPanel parentPanel;
	private SendServerConnection serverConnection;
	private ChatListPopupMenu popup;
	
	public ChatManager(SendServerConnection serverConnection,ContentPanel parent) {
		this.parentPanel = parent;
		this.serverConnection=serverConnection;
		//chatListPanel.setPreferredSize(new Dimension(0,20));
	}

	public HashMap<String, ChatContainer> getChats() {
		return chats;
	}	
	
	public void openChatListPopup()
	{
		popup=new ChatListPopupMenu(chats.values(), new ChatItemWatcher());
		int x=800;
		int y=50;
		popup.show(parentPanel, x,y);
	}
	
	public void closeChat(String to)
	{
		if(chats.containsKey(to))
		{
			chats.get(to).forceClose();
			chats.remove(to);
		}
	}
	
	
	
	public void openChat(String to) {
		if (!chats.containsKey(to)) {
			// Create new chat window
			ChatContainer chat = new ChatContainer(to, parentPanel, new WriteListener());
			chats.put(to, chat);
		} else {
			// Reopen closed windows
			chats.get(to).forceOpen();
		}	
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
	
	private class ChatItemWatcher implements ChatItemListener{

		@Override
		public void removeChat(String name) {
			chats.get(name).forceClose();
			chats.remove(name);
			popup.setVisible(false);
		}
		

		@Override
		public void showChat(String name) {
			chats.get(name).forceOpen();
			popup.setVisible(false);

		}
		
	}

}
