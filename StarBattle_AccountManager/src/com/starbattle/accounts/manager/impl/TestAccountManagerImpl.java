package com.starbattle.accounts.manager.impl;

import java.sql.SQLException;
import java.util.List;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.TestAccountManager;
import com.starbattle.accounts.player.FriendRelation;
import com.starbattle.accounts.player.FriendRelationState;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.validation.RegisterState;

public class TestAccountManagerImpl implements TestAccountManager {

    private AccountManager accountManagerImpl;

    // TODO Auto-generated method stub
    public TestAccountManagerImpl(AccountManager accountManagerImpl) {
        this.accountManagerImpl = accountManagerImpl;
    }

    /**
     * Reset DB, clear all tables
     * 
     * @throws SQLException
     */
    @Override
    public void deleteDbValues() throws AccountException, SQLException {
        accountManagerImpl.resetDB();
    }

    @Override
    public void addTestAccount(String accountName, String displayName, String password, String email) throws AccountException {
        PlayerAccount account = new PlayerAccount(accountName, displayName, password, email);
        if (accountManagerImpl.canRegisterAccount(account) == RegisterState.Register_Ok) {
            accountManagerImpl.registerAccount(account);
        }

    }

    /**
     * Add Friend Relation row with state 0 (Friend) (Or edit value if row is existing)
     */
    @Override
    public void setFriends(String accountNameSender, String displayNameReceiver) throws AccountException {
        accountManagerImpl.newFriendRequest(accountNameSender, displayNameReceiver);
        accountManagerImpl.handleFriendRequest(accountNameSender, displayNameReceiver, true);
    }

    /**
     * Add Friend Relation row with state 1 (Request) (Or edit value if row is existing)
     */
    @Override
    public void setFriendRequest(String accountNameSender, String displayNameReceiver) throws AccountException {
        accountManagerImpl.newFriendRequest(accountNameSender, displayNameReceiver);

    }

    /**
     * Return friend state value of the row
     * 
     * @return
     */
    @Override
    public FriendRelationState getFriendState(String accountNameSender, String displayNameReceiver) throws AccountException {
        List<FriendRelation> friendsList = accountManagerImpl.getFriendRelations(accountNameSender).getFriends();
        for (FriendRelation friendRelation : friendsList) {
            String disp = friendRelation.getDisplayName();
            if (disp.equals(displayNameReceiver)) {
                return friendRelation.getRelationState();
            }
            /*
             * if ((accountNameSender.equalsIgnoreCase(friendRelation.getAccountName ()) && displayNameReceiver.equalsIgnoreCase(friendRelation.getDisplayName ()) ||
             * (accountNameSender .equalsIgnoreCase(friendRelation.getDisplayName()) && displayNameReceiver .equalsIgnoreCase(friendRelation.getAccountName())))) {
             * return friendRelation.getRelationState().getId(); }
             */
        }
        throw new AccountException("No Relation between Players!");
    }

}
