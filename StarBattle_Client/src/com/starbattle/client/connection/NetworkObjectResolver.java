package com.starbattle.client.connection;

import com.starbattle.client.connection.listener.NetworkFriendListener;
import com.starbattle.client.connection.listener.NetworkRegistrationListener;
import com.starbattle.network.connection.objects.NP_ChatMessage;
import com.starbattle.network.connection.objects.NP_LobbyFriends;
import com.starbattle.network.connection.objects.NP_Login;
import com.starbattle.network.connection.objects.NP_StartAnswer;

public class NetworkObjectResolver {

	private NetworkRegistrationListener registrationListener;
	private NetworkFriendListener friendListener;

	public NetworkObjectResolver() {

	}

	public void setRegistrationListener(NetworkRegistrationListener registrationListener) {
		this.registrationListener = registrationListener;
	}

	public void setFriendListener(NetworkFriendListener listener) {
		this.friendListener = listener;
	}

	public void income(Object object) {
		if (object instanceof NP_StartAnswer) {
			NP_StartAnswer answer = (NP_StartAnswer) object;
			if (answer.openGame) {
				registrationListener.registrationOk();
			} else {
				registrationListener.registrationFailed(answer.errorMessage);
			}
		} else if (object instanceof NP_ChatMessage) {
			friendListener.receivedChat((NP_ChatMessage) object);
		} else if (object instanceof NP_LobbyFriends) {
			friendListener.receivedFriendList((NP_LobbyFriends) object);
		}
	}

}
