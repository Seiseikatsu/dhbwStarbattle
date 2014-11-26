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
	private static AccountManagerImpl ami;

	@BeforeClass
	public static void setUpBeforeClass() {
		ami = new AccountManagerImpl();

	/*	account1 = new PlayerAccount("Sebastian1", "RufeMichAn!:)2", "hallo@web.de");
		account2 = new PlayerAccount("Sebastian1", "RufeMichAn!:)2", "hallo@web.de");
		account3 = new PlayerAccount("Geraldine1", "RufeMichAn!:)2", "geri@web.de");
		account4 = new PlayerAccount("Roland1", "29RufeMichAn!:)293", "roland@web.de");
*/
		assertTrue("Registration successful", ami.canRegisterAccount(account1).equals(RegisterState.Register_Ok));
		assertTrue("Registration successful", ami.canRegisterAccount(account3).equals(RegisterState.Register_Ok));
		assertTrue("Registration successful", ami.canRegisterAccount(account4).equals(RegisterState.Register_Ok));
		
		try {
			ami.registerAccount(account1);
			ami.registerAccount(account3);
			ami.registerAccount(account4);
			
			assertTrue(ami.newFriendRequest("Geraldine1", "Sebastian1"));
			assertTrue(ami.newFriendRequest("Geraldine1", "Roland1"));
			assertTrue(ami.newFriendRequest("Roland1", "Sebastian1"));
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void answerRequest() throws AccountException {
		ami.handleFriendRequest("Geraldine1", "Sebastian1", true);
		ami.handleFriendRequest("Geraldine1", "Roland1", true);
		ami.handleFriendRequest("Roland1", "Sebastian1", false);
		
		List<FriendRelation> friends = new ArrayList<>();
		
		friends = ami.getFriendRelations("Geraldine1").getFriends();
		
		assertTrue(friends.size() == 2);
		
	}

	 @AfterClass
	 public static void afterTest() throws AccountException{
	
	/* ami.deleteAccount(ami.getId("Geraldine1"));
	 ami.deleteAccount(ami.getId("Roland1"));
	 ami.deleteAccount(ami.getId("Sebastian1"));*/
	 }



}
