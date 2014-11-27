package com.starbattle.client.views.lobby.control;

import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.chat.ChatManager;
import com.starbattle.client.views.lobby.friends.FriendActionListener;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_HandleFriendRequest;

public class FriendActionReceiver implements FriendActionListener{

	private ChatManager chatManager;
	private SendServerConnection sendServerConnection;
	
	public FriendActionReceiver(ChatManager chatManager, SendServerConnection sendServerConnection) {
		this.chatManager=chatManager;
		this.sendServerConnection=sendServerConnection;
	}
	
	
	@Override
	public void openChat(String friend) {
		chatManager.openChat(friend);
	}

	@Override
	public void delete(String friend) {
		NP_HandleFriendRequest handle=new NP_HandleFriendRequest();
		handle.accept=false;
		handle.friendName=friend;
		sendServerConnection.sendTCP(handle);
	}

	@Override
	public void accept(String friend) {
		NP_HandleFriendRequest handle=new NP_HandleFriendRequest();
		handle.accept=true;
		handle.friendName=friend;
		sendServerConnection.sendTCP(handle);		
	}

}
