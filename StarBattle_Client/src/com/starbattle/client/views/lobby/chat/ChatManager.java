package com.starbattle.client.views.lobby.chat;

import java.util.HashMap;

import com.starbattle.client.window.ContentPanel;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_ChatMessage;

public class ChatManager {

	private HashMap<String, ChatContainer> chats = new HashMap<String, ChatContainer>();
	private ContentPanel parentPanel;
	private SendServerConnection serverConnection;
	
	public ChatManager(SendServerConnection serverConnection,ContentPanel parent) {
		this.parentPanel = parent;
		this.serverConnection=serverConnection;
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

}
