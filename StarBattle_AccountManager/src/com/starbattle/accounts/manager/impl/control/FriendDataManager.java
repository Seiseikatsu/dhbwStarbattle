package com.starbattle.accounts.manager.impl.control;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlInsertStatement;
import com.starbattle.accounts.manager.impl.sql.SqlSelectStatement;
import com.starbattle.accounts.manager.impl.tables.FriendTable;
import com.starbattle.accounts.player.FriendRelation;
import com.starbattle.accounts.player.FriendRelationState;
import com.starbattle.accounts.player.PlayerFriends;

public class FriendDataManager extends DataController {

	public FriendDataManager(DatabaseControl databaseControl) {
		super(databaseControl);
	}

	public PlayerFriends getFriendRelations(String accountName) throws AccountException {
		try {

			PlayerFriends friends = new PlayerFriends();
			int accountId = getAccountIdForAccountname(accountName);
			
			SqlSelectStatement select = new SqlSelectStatement();
			select.select(FriendTable.ACCOUNT_ID_FRIEND, FriendTable.STATUS);
			select.from(FriendTable.class);
			select.where(FriendTable.ACCOUNT_ID);
			select.values(accountId);
			
			ResultSet rsFriendRequester = select.execute(databaseControl);

			SqlSelectStatement selectFriendAnswer = new SqlSelectStatement();
			selectFriendAnswer.select(FriendTable.ACCOUNT_ID, FriendTable.STATUS);
			selectFriendAnswer.from(FriendTable.class);
			selectFriendAnswer.where(FriendTable.ACCOUNT_ID_FRIEND);

			ResultSet rsFriendAnswer = selectFriendAnswer.execute(databaseControl);

			while (rsFriendRequester.next()) {
				int accountIDFriend = rsFriendRequester.getInt("account_id_friend");
				int status = rsFriendRequester.getInt("status");
				String accountNameFriend = getAccountNameForAccountID(accountIDFriend);
				String displayNameFriend = getDisplayNameForAccountID(accountIDFriend);

				if (status == 1) {
					// Request
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Request));
				} else {
					// Friends
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Friends));
				}
			}

			while (rsFriendAnswer.next()) {
				int accountIDFriend = rsFriendAnswer.getInt("account_id");
				int status = rsFriendAnswer.getInt("status");
				String accountNameFriend = getAccountNameForAccountID(accountIDFriend);
				String displayNameFriend = getDisplayNameForAccountID(accountIDFriend);

				if (status == 1) {
					// Pending
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Pending));
				} else {
					// Friends
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Friends));
				}
			}
			return friends;
		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}
	}

	public boolean newFriendRequest(String accountName, String friendDisplayname) throws AccountException {
		int accountId = getAccountIdForAccountname(accountName);
		int accountIdFriend = 0;
		try {
			accountIdFriend = getAccountIdForDisplayname(friendDisplayname);
		} catch (Exception e) {
			return false; // no friend for display name found
		}

		try {
			SqlInsertStatement insert = new SqlInsertStatement();
			insert.insert(FriendTable.class);
			insert.into(FriendTable.ACCOUNT_ID, FriendTable.ACCOUNT_ID_FRIEND, FriendTable.STATUS);
			insert.values(accountId, accountIdFriend, 1);
			
			insert.execute(databaseControl);

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}
		return true;
	}

	public String handleFriendRequest(String accountName, String displayNameFriend, boolean accept)
			throws AccountException {
		return null;
	}

}
