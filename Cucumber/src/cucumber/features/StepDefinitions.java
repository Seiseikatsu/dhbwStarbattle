package cucumber.features;

import static org.junit.Assert.assertEquals;

import com.starbattle.accounts.validation.LoginState;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.network.connection.objects.NP_StartAnswer;
import com.starbattle.server.manager.PlayerManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	private ClientAutomate client, anotherClient;

	@cucumber.api.java.Before	
	public void before()
	{
		System.out.println("BEFORE");
	}
	
	@cucumber.api.java.After
	public void after()
	{
		System.out.println("AFTER");
		if (client != null) {
			client.shutdown();
		}
		if (anotherClient != null) {
			anotherClient.shutdown();
		}
	}
	
	@Given("^I start my application$")
	public void i_start_my_application() throws Throwable {
		client = ClientTestInterface.createNewTestClient();
	}

	@Given("^I am on the login view$")
	public void i_am_on_the_login_view() throws Throwable {
		assertEquals(true, client.isInView(LoginView.VIEW_ID));
	}

	@When("^I type \"(.*?)\" in \"(.*?)\"$")
	public void i_type_in(String text, String field) throws Throwable {
		client.fillInTextfield(field, text);
	}

	@When("^I click on button \"(.*?)\"$")
	public void i_click_on_button(String buttonName) throws Throwable {
		client.clickButton(buttonName);
	}

	@Then("^I am on the lobby view$")
	public void i_am_on_the_lobby_view() throws Throwable {
		assertEquals(true, client.isInView(LobbyView.VIEW_ID));
	}


	@Then("^I receive an error message saying \"(.*?)\"$")
	public void i_receive_an_error_message_saying(String error) throws Throwable {

		NP_StartAnswer startUp = (NP_StartAnswer) client.waitForNetworkReceive(NP_StartAnswer.class);
		String message = startUp.errorMessage;
		switch (error) {
		case "Wrong Username":
			assertEquals(message, LoginState.Wrong_Username.getText());
			break;
		case "Wrong Password":
			assertEquals(message, LoginState.Wrong_Password.getText());
			break;
		case "User already logged in":
			assertEquals(message, PlayerManager.playerAlreadyLoginMessage);
			break;
		}
	}

	@Given("^another application is logged in with \"(.*?)\" and \"(.*?)\"$")
	public void another_application_is_logged_in_with_and(String name, String pw) throws Throwable {

		anotherClient = ClientTestInterface.createNewTestClient();
		anotherClient.doLogin(name, pw);
		
	}

}
