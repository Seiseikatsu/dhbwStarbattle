package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.AccountManagerFacade;
import com.starbattle.accounts.manager.impl.AccountManagerImpl;
import com.starbattle.accounts.player.FriendRelation;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AddFriendsTest {
	static DatabaseConnection db;
	static private PlayerAccount account1;
	static private PlayerAccount account2;
	static private PlayerAccount account3;
	static private PlayerAccount account4;
	private static AccountManagerFacade facade;

	@BeforeClass
	public static void setUpBeforeClass() throws AccountException {
		facade = new AccountManagerFacade();

		account1 = new PlayerAccount("Sebastian1","Sebi", "RufeMichAn!:)2", "hallo@web.de");
		account3 = new PlayerAccount("Geraldine1","Gerii", "RufeMichAn!:)2", "geri@web.de");
		account4 = new PlayerAccount("Roland1","Rollii", "29RufeMichAn!:)293", "roland@web.de");

		assertTrue("Registration successful", facade.canRegisterAccount(account1).equals(RegisterState.Register_Ok));
		assertTrue("Registration successful", facade.canRegisterAccount(account3).equals(RegisterState.Register_Ok));
		assertTrue("Registration successful", facade.canRegisterAccount(account4).equals(RegisterState.Register_Ok));
		
		try {
			facade.registerAccount(account1);
			facade.registerAccount(account3);
			facade.registerAccount(account4);
			
			assertTrue(facade.newFriendRequest("Geraldine1", "Sebastian1"));
			assertTrue(facade.newFriendRequest("Geraldine1", "Roland1"));
			assertTrue(facade.newFriendRequest("Roland1", "Sebastian1"));
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void answerRequest() throws AccountException {
		facade.handleFriendRequest("Geraldine1", "Sebastian1", true);
		facade.handleFriendRequest("Geraldine1", "Roland1", true);
		facade.handleFriendRequest("Roland1", "Sebastian1", false);
		
		List<FriendRelation> friends = new ArrayList<>();
		
		friends = facade.getFriendRelations("Geraldine1").getFriends();
		
		assertTrue(friends.size() == 2);
		
	}

	 @AfterClass
	 public static void afterTest() throws AccountException{
	
	facade.deleteAccount("Geraldine1");
	facade.deleteAccount("Roland1");
	facade.deleteAccount("Sebastian1");
	 }



}
