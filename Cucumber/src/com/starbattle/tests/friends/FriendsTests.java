package com.starbattle.tests.friends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.player.FriendRelationState;
import com.starbattle.client.testinterface.exceptions.GUIElementNotFoundException;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.client.testinterface.tester.ToleranceCheckTask;
import com.starbattle.client.views.lobby.friends.AddFriendView;
import com.starbattle.client.views.lobby.friends.FriendRelation;
import com.starbattle.tests.TestEnvironment;
import com.starbattle.tests.TestUsersConfig;

public class FriendsTests {

	private static TestEnvironment testEnvironment;

	private static String myAccountName, myUserName, myPassword, friendUserName, friendAccountName, friendPassword;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testEnvironment = new TestEnvironment();
		myAccountName = TestUsersConfig.getAccountName(0);
		myUserName = TestUsersConfig.getDisplayName(0);
		myPassword = TestUsersConfig.getPassword(0);
		friendUserName = TestUsersConfig.getDisplayName(1);
		friendAccountName = TestUsersConfig.getAccountName(1);
		friendPassword = TestUsersConfig.getPassword(1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if(testEnvironment!=null)
		{
		testEnvironment.close();
		}
	}

	@Before
	public void setUp() throws Exception {
		// reset db values
		testEnvironment.setUp();
	}

	@After
	public void tearDown() throws Exception {
		testEnvironment.tidyUp();
	}

	@Test
	public void addFriend() throws Exception {

		ClientAutomate client = testEnvironment.getClient();
		client.doLogin(myAccountName, myPassword);
		client.clickButton("AddFriendInLobby");
		assertEquals(true, client.isInView(AddFriendView.VIEW_ID));
		client.fillInTextfield("FriendUsername", TestUsersConfig.getDisplayName(1));
		client.clickButton("AddFriend");

		// check new entry in DB (value =1 = Request)

		assertTrue(client.check(new ToleranceCheckTask() {
			@Override
			public boolean check() {
				try {
					FriendRelationState relation = testEnvironment.getTestAccountManager().getFriendState(
							myAccountName, friendUserName);
					return relation != FriendRelationState.Friends;
				} catch (AccountException e) {
				}
				return false;
			}
		}));

		// check my gui
		assertTrue(client.friendRelationStateIs(friendUserName, FriendRelation.RELATION_PENDING));
		// kill my client
		client.shutdown();

		// login friend in new client

		client = testEnvironment.openAnotherClient();
		client.doLogin(friendAccountName, friendPassword);

		// check friend gui
		assertTrue(client.friendRelationStateIs(myUserName, FriendRelation.RELATION_REQUEST));
	}

	@Test
	public void acceptRequest() throws Exception {

		testEnvironment.getTestAccountManager().setFriendRequest(friendAccountName, myUserName);

		ClientAutomate client = testEnvironment.getClient();
		client.doLogin(myAccountName, myPassword);
		client.acceptFriendRequest(friendUserName);

		assertTrue(testEnvironment.getTestAccountManager().getFriendState(friendAccountName, myUserName).getId() == 0);

		// check my gui
		assertTrue(client.friendRelationStateIs(friendUserName, FriendRelation.RELATION_FRIENDS));
		// kill my client
		client.shutdown();

		// login friend in new client
		client = testEnvironment.openAnotherClient();
		client.doLogin(friendAccountName, friendPassword);

		// check friend gui
		assertTrue(client.friendRelationStateIs(myUserName, FriendRelation.RELATION_FRIENDS));
	}

	@Test
	public void rejectRequest() throws Exception {

		testEnvironment.getTestAccountManager().setFriendRequest(friendAccountName, myUserName);

		ClientAutomate client = testEnvironment.getClient();
		client.doLogin(myAccountName, myPassword);
		client.removeFriend(friendUserName);

		assertTrue(client.check(new ToleranceCheckTask() {

			@Override
			public boolean check() {
				try {
					testEnvironment.getTestAccountManager().getFriendState(friendAccountName, myUserName);

				} catch (AccountException e) {
					return true;
				}
				return false;
			}
		}));

		// check my gui

		assertTrue(!client.hasFriendRelation(friendUserName));
		// kill my client
		client.shutdown();

		// login friend in new client
		client = testEnvironment.openAnotherClient();
		client.doLogin(friendAccountName, friendPassword);

		// check friend gui
		assertTrue(!client.hasFriendRelation(myUserName));
	}

	@Test
	public void friendOnlineStatus() throws Exception {

		// set friend relation between me and user2
		testEnvironment.getTestAccountManager().setFriends(myAccountName, friendUserName);

		// log me in
		final ClientAutomate client = testEnvironment.getClient();
		client.doLogin(myAccountName, myPassword);

		// check if friend is shown as offline:
		assertTrue(client.checkFriendOffline(friendUserName));

		// login friend
		ClientAutomate friend = testEnvironment.openAnotherClient();

		friend.doLogin(friendAccountName, friendPassword);

		// check if friend is shown as online
		assertTrue(client.checkFriendOnline(friendUserName));

		// logout friend
		testEnvironment.closeAnotherClient();

		// check if friend is shown as offline
		assertTrue(client.checkFriendOffline(friendUserName));

	}
}
