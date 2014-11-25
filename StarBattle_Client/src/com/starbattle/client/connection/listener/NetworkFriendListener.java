package com.starbattle.client.connection.listener;

import com.starbattle.network.connection.objects.NP_ChatMessage;
import com.starbattle.network.connection.objects.NP_FriendUpdate;
import com.starbattle.network.connection.objects.NP_LobbyFriends;

public interface NetworkFriendListener {

	public void receivedChat(NP_ChatMessage chat);
	
	public void receivedFriendList(NP_LobbyFriends friends);
	
	public void receivedFriendUpdate(NP_FriendUpdate update);
	
}
