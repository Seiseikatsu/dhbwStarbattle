package com.starbattle.client.connection;

import com.starbattle.client.connection.listener.NetworkBattleResultsListener;
import com.starbattle.client.connection.listener.NetworkFriendListener;
import com.starbattle.client.connection.listener.NetworkFriendRequestListener;
import com.starbattle.client.connection.listener.NetworkGameListener;
import com.starbattle.client.connection.listener.NetworkGameQueryListener;
import com.starbattle.client.connection.listener.NetworkRegistrationListener;
import com.starbattle.network.connection.objects.NP_BattleResults;
import com.starbattle.network.connection.objects.NP_CancelMatchQueue;
import com.starbattle.network.connection.objects.NP_ChatException;
import com.starbattle.network.connection.objects.NP_ChatMessage;
import com.starbattle.network.connection.objects.NP_FriendRequestAnswer;
import com.starbattle.network.connection.objects.NP_FriendUpdate;
import com.starbattle.network.connection.objects.NP_GameModesList;
import com.starbattle.network.connection.objects.NP_LobbyFriends;
import com.starbattle.network.connection.objects.NP_ServerStop;
import com.starbattle.network.connection.objects.NP_StartAnswer;
import com.starbattle.network.connection.objects.game.NP_GameEnd;
import com.starbattle.network.connection.objects.game.NP_GameException;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class NetworkObjectResolver {

	private NetworkRegistrationListener registrationListener;
	private NetworkFriendListener friendListener;
	private NetworkFriendRequestListener friendRequestListener;
	private NetworkGameListener gameListener;
	private NetworkGameQueryListener queryListener;
	private NetworkBattleResultsListener battleResultsListener;

	public NetworkObjectResolver() {

	}

	
	public void setBattleResultsListener(NetworkBattleResultsListener battleResultsListener) {
		this.battleResultsListener = battleResultsListener;
	}
	
	public void setQueryListener(NetworkGameQueryListener queryListener) {
		this.queryListener = queryListener;
	}

	public void setRegistrationListener(NetworkRegistrationListener registrationListener) {
		this.registrationListener = registrationListener;
	}

	public void setFriendListener(NetworkFriendListener listener) {
		this.friendListener = listener;
	}

	public void setFriendRequestListener(NetworkFriendRequestListener friendRequestListener) {
		this.friendRequestListener = friendRequestListener;
	}

	public void setGameListener(NetworkGameListener gameListener) {
		this.gameListener = gameListener;
	}

	public void income(Object object) {

		if (object instanceof NP_StartAnswer) {
			NP_StartAnswer answer = (NP_StartAnswer) object;
			if (answer.openGame) {
				registrationListener.registrationOk(answer.answerMessage);
			} else {
				registrationListener.registrationFailed(answer.answerMessage);
			}
		} else if (object instanceof NP_ChatMessage) {
			friendListener.receivedChat((NP_ChatMessage) object);
		} else if (object instanceof NP_LobbyFriends) {
			friendListener.receivedFriendList((NP_LobbyFriends) object);
		} else if (object instanceof NP_FriendUpdate) {
			friendListener.receivedFriendUpdate((NP_FriendUpdate) object);
		} else if (object instanceof NP_ChatException) {
			friendListener.receivedChatException((NP_ChatException) object);
		} else if (object instanceof NP_FriendRequestAnswer) {
			friendRequestListener.receivedFriendRequestAnswer((NP_FriendRequestAnswer) object);
		} else if (object instanceof NP_GameUpdate) {
			gameListener.receivedGameUpdate((NP_GameUpdate) object);
		} else if (object instanceof NP_GameStart) {
			gameListener.receivedGameStart((NP_GameStart) object);
		} else if (object instanceof NP_GameEnd) {
			gameListener.receivedGameEnd((NP_GameEnd) object);
		} else if (object instanceof NP_GameException) {
			gameListener.receivedGameException((NP_GameException) object);
		} else if (object instanceof NP_PrepareGame) {
			gameListener.receivedPrepareGame((NP_PrepareGame) object);
		} else if (object instanceof NP_GameModesList) {
			queryListener.receivedGameModes((NP_GameModesList) object);
		} else if (object instanceof NP_CancelMatchQueue) {
			queryListener.receivedQueryCancel();
		} else if(object instanceof NP_BattleResults){
			battleResultsListener.receivedBattleResults((NP_BattleResults)object);
		}

	}

}
