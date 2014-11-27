package com.starbattle.client.connection.listener;

import com.starbattle.network.connection.objects.NP_FriendRequestAnswer;

public interface NetworkFriendRequestListener {

	public void receivedFriendRequestAnswer(NP_FriendRequestAnswer answer);
	
}
