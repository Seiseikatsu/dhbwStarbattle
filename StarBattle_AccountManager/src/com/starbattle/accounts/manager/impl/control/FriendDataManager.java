package com.starbattle.accounts.manager.impl.control;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.player.PlayerFriends;

public class FriendDataManager extends DataController {

	public FriendDataManager(DatabaseControl databaseControl) {
		super(databaseControl);
	}

	public PlayerFriends getFriendRelations(String accountName) throws AccountException {
		return null;
	}

	public boolean newFriendRequest(String accountName, String friendDisplayname) throws AccountException {
		return false;
	}

	public String handleFriendRequest(String accountName, String displayNameFriend, boolean accept)
			throws AccountException {
		return null;
	}

}
