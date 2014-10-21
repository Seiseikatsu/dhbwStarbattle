package com.starbattle.accounts.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFriends {

	private List<FriendRelation> friends;

	public PlayerFriends() {

		friends = new ArrayList<FriendRelation>();

	}

	public void addRelation(FriendRelation friend) {
		friends.add(friend);
	}

	public List<FriendRelation> getFriends() {
		return friends;
	}
}
