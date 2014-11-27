package com.starbattle.client.views.lobby.chat;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.starbattle.client.window.ContentPanel;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_ChatException;
import com.starbattle.network.connection.objects.NP_ChatMessage;

public class ChatManager {

	private HashMap<String, ChatContainer> chats = new HashMap<String, ChatContainer>();
	private ContentPanel parentPanel;
	private SendServerConnection serverConnection;
	private ChatListPanel chatListPanel;

	public ChatManager(SendServerConnection serverConnection, ContentPanel parent) {
		this.parentPanel = parent;
		this.serverConnection = serverConnection;
		this.chatListPanel = new ChatListPanel(this, new ChatItemWatcher());
		// chatListPanel.setPreferredSize(new Dimension(0,20));
	}

	public HashMap<String, ChatContainer> getChats() {
		return chats;
	}

	public void closeChat(String to) {
		if (chats.containsKey(to)) {
			chats.get(to).forceClose();
			chats.remove(to);
			chatListPanel.update();
		}
	}

	public void openChat(String to) {
		if (!chats.containsKey(to)) {
			// Create new chat window
			ChatContainer chat = new ChatContainer(to, parentPanel, new WriteListener());
			chats.put(to, chat);
			chatListPanel.update();
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
	
	public void receiveException(NP_ChatException chat) {
		String from=chat.chatName;
		if (chats.containsKey(from)) {
			chats.get(from).receiveException("Message could not be send");
		}
	}

	private class WriteListener implements WriteMessageListener {

		@Override
		public void writeMessage(String to, String text) {

			// Send Chat Message
			NP_ChatMessage message = new NP_ChatMessage();
			message.name = to;
			message.message = text;
			serverConnection.sendTCP(message);
		}

	}

	private class ChatItemWatcher implements ChatItemListener {

		@Override
		public void removeChat(String name) {
			closeChat(name);
		}

		@Override
		public void showChat(String name) {
			openChat(name);
		}

	}

	public ChatListPanel getChatListPanel() {
		return chatListPanel;
	}



}
