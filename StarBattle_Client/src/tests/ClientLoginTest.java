package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.testAPI.ClientAutomate;
import com.starbattle.client.testAPI.GUIElementNotFoundException;
import com.starbattle.client.testAPI.NetworkTimeoutException;
import com.starbattle.client.testAPI.WrongGUIElementException;
import com.starbattle.network.connection.objects.NP_StartAnswer;

public class ClientLoginTest {

	private ClientAutomate tester;

	@Before
	public void create() {
		StarBattleClient client = new StarBattleClient();
		tester = client.getAutomatedTester();
	}

	@Test
	public void testLogin() {

		try {

			tester.fillIn("Login_Name", "TimoTester");
			tester.fillIn("Login_Password", "Timotest#1");
			tester.click("Button_Login");
			NP_StartAnswer start = (NP_StartAnswer) tester.waitForNetworkReceive(NP_StartAnswer.class);
			assertEquals(true, start.openGame);

		} catch (NetworkTimeoutException e) {
			e.printStackTrace();
		} catch (GUIElementNotFoundException | WrongGUIElementException e) {
			e.printStackTrace();
		}

	}

}
