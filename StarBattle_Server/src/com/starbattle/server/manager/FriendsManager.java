package com.starbattle.server.manager;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.player.FriendRelation;
import com.starbattle.accounts.player.PlayerFriends;
import com.starbattle.network.connection.objects.NP_ChatMessage;
import com.starbattle.network.connection.objects.NP_Constants;
import com.starbattle.network.connection.objects.NP_FriendRequest;
import com.starbattle.network.connection.objects.NP_FriendUpdate;
import com.starbattle.network.connection.objects.NP_HandleFriendRequest;
import com.starbattle.network.connection.objects.NP_LobbyFriends;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.server.player.PlayerContainer;

public class FriendsManager {

	private PlayerContainer playerContainer;
	private AccountManager accountManager;
	
	public FriendsManager(PlayerContainer playerContainer, AccountManager accountManager)
	{
		this.accountManager=accountManager;
		this.playerContainer=playerContainer;
	}
	
	
	public void updateFriendsMyOnlineStatus(String account, boolean imOnline) {
		try {
			String myDisplayName = accountManager.getDisplayName(account);
			PlayerFriends friends = accountManager.getFriendRelations(account);
			for (FriendRelation relation : friends.getFriends()) {
				// update if they are my friends and not requests or pending
				if (relation.getRelationState().getId() == NP_Constants.FRIEND_STATE_FRIENDS) {
					String friendAccount = relation.getAccountName();
					// Send just if they are connected
					if (playerContainer.playerConnected(friendAccount)) {
						NP_FriendUpdate update = new NP_FriendUpdate();
						update.name = myDisplayName;
						update.online = imOnline;
						update.updateType = NP_Constants.FRIEND_UPDATE_TYPE_ONLINEUPDATE;
						playerContainer.getPlayer(friendAccount).getConnection().sendTCP(update);
					}
				}
			}
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	private void sendFriendUpdate(String selfAccount, String friendAccount, int relationType, boolean online) {
		try {
			NP_FriendUpdate update = new NP_FriendUpdate();
			update.name = accountManager.getDisplayName(selfAccount); // to
																		// player
																		// 2
			update.online = online;
			update.updateType = relationType;
			// Send to Player 2 new relation update, if player 2 is connected
			if (playerContainer.playerConnected(friendAccount)) {
				playerContainer.getPlayer(friendAccount).getConnection().sendTCP(update);
			}
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	public void handleFriendRequest(PlayerConnection player, NP_HandleFriendRequest handle) {
		String name = handle.friendName;
		boolean accept = handle.accept;
		try {
			String myAccount = player.getAccountName();
			String friendAccountName = accountManager.handleFriendRequest(player.getAccountName(), name, accept);
			int state = NP_Constants.FRIEND_UPDATE_TYPE_ADDFRIEND;
			if (accept == false) {
				state = NP_Constants.FRIEND_UPDATE_TYPE_DELTEFRIEND;
			}
			// Send update to both accounts
			boolean imOnline = playerContainer.playerConnected(myAccount);
			sendFriendUpdate(myAccount, friendAccountName, state, imOnline);
			boolean friendOnline = playerContainer.playerConnected(friendAccountName);
			sendFriendUpdate(friendAccountName, myAccount, state, friendOnline);
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	public void trySendFriendRequest(PlayerConnection player, NP_FriendRequest friendRequest) {
		String friendDisplayname = friendRequest.inputName;
		try {
			if (accountManager.newFriendRequest(player.getAccountName(), friendDisplayname)) {
				//sendCurrentFriends(player); // send update to client
			    
				//send update to me (state: pending)
				NP_FriendUpdate update=new NP_FriendUpdate();
				update.name=friendDisplayname;
				update.updateType=NP_Constants.FRIEND_UPDATE_TYPE_ADDFRIENDPENDING;
				player.sendTCP(update);
				
				//send update to friend (state: request)
				update=new NP_FriendUpdate();
				update.name=accountManager.getDisplayName(player.getAccountName());
				update.updateType=NP_Constants.FRIEND_UPDATE_TYPE_ADDFRIENDREQUEST;
				player.sendTCP(update);
				
			}
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	public void sendCurrentFriends(PlayerConnection player) {
		try {
			PlayerFriends friends = accountManager.getFriendRelations(player.getAccountName());
			NP_LobbyFriends npfriends = new NP_LobbyFriends();
			for (FriendRelation f : friends.getFriends()) {

				String friendAccountName = f.getAccountName();
				String friendDisplayName = accountManager.getDisplayName(friendAccountName);
				npfriends.friendNames.add(friendDisplayName);
				npfriends.relationStates.add((byte) f.getRelationState().getId());
				boolean online = playerContainer.playerConnected(friendAccountName);
				npfriends.friendOnline.add(online);
			}
			player.sendTCP(npfriends);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendChat(PlayerConnection player, NP_ChatMessage message) {
		try {
			// Get Displayname for Accountname of the sending player
			String fromDisplayname = accountManager.getDisplayName(player.getAccountName());
			// Get the Accountname (to get the connection) of the receiver
			String toAccountName = accountManager.getAccountName(message.name);

			// Create chat message
			NP_ChatMessage chat = new NP_ChatMessage();
			chat.message = message.message;
			chat.name = fromDisplayname;
			// Send chat to receiver player
			playerContainer.getPlayer(toAccountName).getConnection().sendTCP(chat);
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}
	
}
