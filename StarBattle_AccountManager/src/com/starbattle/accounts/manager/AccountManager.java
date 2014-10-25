package com.starbattle.accounts.manager;

import java.util.List;

import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.player.PlayerFriends;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public interface AccountManager {

	public void registerAccount(PlayerAccount account) throws AccountException;

	public void deleteAccount(int id) throws AccountException;

	public LoginState canLogin(String name, String password) throws AccountException;

	public RegisterState canRegisterAccount(PlayerAccount account);

	public PlayerAccount readAccount(String accountName) throws AccountException;

	public void updateAccount(String accountName, AccountUpdate update);
	
	public List<Integer> getItemList(int playerId) throws AccountException;
	
	public void tryResetPassword(String accountName, String email) throws AccountException;

	
	
	
		
	/**
	 * Returns all Friendrelations for an account
	 * 
	 * @param accountName
	 * @return PlayerFriends object
	 * @throws AccountException 
	 */
	public PlayerFriends getFriendRelations(String accountName) throws AccountException;
	
	/**
	 * Trying to create a new friend request for the account with name accountName.
	 * If friendDisplayname doesnt exist or the player is already in a relation
	 * with the account, returns false.
	 * 
	 * @param accountName
	 * @param friendDisplayname
	 * @return true or false 
	 * @throws AccountException 
	 */
	public boolean newFriendRequest(String accountName, String friendDisplayname) throws AccountException;
	
	
	/**
	 * 
	 * User reaction to a friend request:
	 * 
	 * Account (accountName) handles friend relation (which is in pending state therefore)
	 * with the player account for the displayname (friendDisplayname).
	 * 
	 * accept = true then change state to Friends
	 * accept = false remove relation in database between both accounts
	 * 
	 * @param accountName
	 * @param friendDisplayname
	 * @param accept
	 * @throws AccountException 
	 */
	public void handleFriendRequest(String accountName, String accountNameFriend, boolean accept) throws AccountException;

	
}
