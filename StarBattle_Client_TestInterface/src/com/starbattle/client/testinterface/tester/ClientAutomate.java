package com.starbattle.client.testinterface.tester;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.starbattle.client.main.StarBattleClient;
import com.starbattle.client.testinterface.exceptions.GUIElementNotFoundException;
import com.starbattle.client.testinterface.exceptions.LoginFailureException;
import com.starbattle.client.testinterface.exceptions.NetworkTimeoutException;
import com.starbattle.client.testinterface.exceptions.WrongGUIElementException;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.chat.ChatManager;
import com.starbattle.client.views.lobby.friends.FriendRelation;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.client.views.register.validate.PasswordHasher;
import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.GameWindow;
import com.starbattle.network.connection.objects.NP_Login;

public class ClientAutomate {

	private ClientNetworkInterface clientNetwork;
	private GameWindow window;
	
	private StarBattleClient client;
	
	public ClientAutomate(StarBattleClient client) {

		clientNetwork = new ClientNetworkInterface(client.getConnection());
		this.window = client.getWindow();
		this.client=client;
	}
	
	public void hideClientWindow()
	{
		//minimize window
		window.getWindow().setState(JFrame.ICONIFIED);
	}
	
	/*
	 * DO STEP METHODES
	 * 
	 */

	public void doLogin(String accountName, String password) throws LoginFailureException {
		step();
		setInView(LoginView.VIEW_ID);
		String pw = PasswordHasher.hashPassword(password);
		NP_Login login = new NP_Login();
		login.password = pw;
		login.playerName = accountName;
		clientNetwork.sendTCP(login);
		if(!isInView(LobbyView.VIEW_ID))
		{
			throw new LoginFailureException("Failed to login with "+accountName+" "+password);
		}
	}

	public void clickButton(String buttonName) throws GUIElementNotFoundException, WrongGUIElementException {
		step();
			JComponent component = findGUI(buttonName);
			if (component != null) {
				if (component instanceof JButton) {
					JButton b = (JButton) component;
					b.doClick();
				} else {
					throw new WrongGUIElementException(component.getClass(), JButton.class);
				}
			} else {
				throw new GUIElementNotFoundException(buttonName);
			}
	}

	public void fillInTextfield(String textfieldName, String text) throws GUIElementNotFoundException, WrongGUIElementException {
		step();
			JComponent component = findGUI(textfieldName);
			if (component != null) {
				if (component instanceof JTextField) {
					JTextField b = (JTextField) component;
					b.setText(text);
				} else {
					throw new WrongGUIElementException(component.getClass(), JTextField.class);
				}
			} else {
				throw new GUIElementNotFoundException(textfieldName);
			}
	}

	public void setInView(int viewID) {
		step();
		window.getContent().showView(viewID);
	}
	
	
	/*
	 * CHECK STEP METHODES
	 */
	
	public int getChatMessagesCount(final String chatFriendName) throws GUIElementNotFoundException
	{
	
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check(){
				LobbyView lobby = getLobbyView();
				ChatManager chatM=lobby.getChatManager();
				if(chatM.getChats().containsKey(chatFriendName))
				{
					return true;
				}
				return false;
			}
		});
		
		if(check.isCheckOk())
		{
			LobbyView lobby = getLobbyView();
			return lobby.getChatManager().getChats().get(chatFriendName).getView().getChatContent().getComponentCount();
		}
		else
		{
			throw new GUIElementNotFoundException("Couldnt find Chat to '"+chatFriendName+"'");
		}
	}
	
	public boolean hasChatToFriend(final String friendName) 
	{
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check(){
				LobbyView lobby = getLobbyView();
				ChatManager chatM=lobby.getChatManager();
				return chatM.getChats().containsKey(friendName);
			}
		});
		return check.isCheckOk();
	}
	

	public boolean isInView(final int viewID) {
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check() {
				int view=window.getContent().getCurrentViewID();
				//System.out.println("CHECK "+viewID+" =? "+view);
				if (viewID == view) {
					return true;
				}
				return false;
			}
		});
		return check.isCheckOk();
	}

	public boolean friendRelationStateIs(final String friend, final int relationType) {
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check() {
				LobbyView lobby = getLobbyView();
				FriendRelation relation = lobby.getFriendPanel().getFriendRelationTo(friend);
				if (relation != null) {
					if (relationType == relation.getState()) {
						return true;
					}
				}
				return false;
			}
		});
		return check.isCheckOk();
	}

	public boolean hasFriendRelation(final String friend) {
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check() {
				LobbyView lobby = getLobbyView();
				FriendRelation relation = lobby.getFriendPanel().getFriendRelationTo(friend);
				if (relation != null) {
					return true;
				}
				return false;
			}
		});
		return check.isCheckOk();
	}

	private LobbyView getLobbyView()
	{
		ContentView view = window.getContent().getViews().get(LobbyView.VIEW_ID);
		LobbyView lobby = (LobbyView) view;
		return lobby;
	}
	
	public String getPwError() throws GUIElementNotFoundException, WrongGUIElementException {
		String message = "";
		step();
		JComponent component = findGUI("Error_Text");
		if (component != null) {
			if (component instanceof JLabel) {
				JLabel l = (JLabel) component;
				message = l.getText();
			} else {
				throw new WrongGUIElementException(component.getClass(), JButton.class);
			}
		} else {
			throw new GUIElementNotFoundException("Error_Text");
		}
		return message;
	}
	
	public Object waitForNetworkReceive(final Class<?> requestedObject) throws NetworkTimeoutException {
		return clientNetwork.waitForNetworkReceive(requestedObject);
	}

	private JComponent findGUI(String name) {

		return ComponentFinder.findElement(name, window.getContent());
	}

	private void step() {
		try {
			Thread.sleep((long) (1000f * ClientTestInterface.stepDelay));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void shutdown()
	{
		
		client.shutdown();
	}
	
}
