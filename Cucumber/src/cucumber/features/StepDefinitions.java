package cucumber.features;

import static org.junit.Assert.assertEquals;

import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.network.connection.objects.NP_StartAnswer;
import com.starbattle.server.main.StarbattleServer;
import com.starbattle.server.manager.PlayerManager;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	private ClientAutomate client;
	private StarbattleServer server;
	
	@cucumber.api.java.Before	
	public void init()
	{
		
		//start server
		server=new StarbattleServer();

		//set simulation parameters
		ClientTestInterface.shutdownDelaySeconds=1;
		ClientTestInterface.stepDelay=1f;
		//init default application
		client = ClientTestInterface.createNewTestClient();		
	}
	
	@cucumber.api.java.After
	public void tidyUp()
	{
		//shut down all applications from this test
		ClientTestInterface.shutdown();	
		
		//end server
		server.close();
	}

	@Given("^I am on the login view$")
	public void i_am_on_the_login_view() throws Throwable {
		assertEquals(true, client.isInView(LoginView.VIEW_ID));
	}

	@Then("^I delete user \"(.*?)\"$")
	public void i_delete_user(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am on the register view$")
	public void i_am_on_the_register_view() throws Throwable {
		assertEquals(true, client.isInView(RegisterView.VIEW_ID));
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
		String message = startUp.answerMessage;
		assertEquals(true, startUp.openGame); //check if its errror
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
		case "Existing Accountname":
			assertEquals(message, RegisterState.Accountname_Exists.getText());
			break;
		case "Existing Displayname":
			assertEquals(message, RegisterState.Displayname_Exists.getText());
			break;
		case "Invalid Accountname":
			assertEquals(message, RegisterState.Accountname_Invalid.getText());
			break;
		case "Invalid Displayname":
			assertEquals(message, RegisterState.Displayname_Invalid.getText());
			break;	
		}
	}

	@Given("^another application is logged in with \"(.*?)\" and \"(.*?)\"$")
	public void another_application_is_logged_in_with_and(String name, String pw) throws Throwable {

		ClientAutomate anotherClient = ClientTestInterface.createNewTestClient();
		anotherClient.doLogin(name, pw);
		anotherClient.hideClientWindow(); //minimize second client window so we can see our target client
	}
	
	@Given("^Given I am logged in as \"(.*?)\" with password \"(.*?)\"$")
	public void i_am_logged_in_as_with_password(String name, String pw) throws Throwable {
		client.doLogin(name, pw);
	}
	

}
