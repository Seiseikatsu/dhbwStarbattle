package com.starbattle.client.testinterface.tester;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.testinterface.exceptions.GUIElementNotFoundException;
import com.starbattle.client.testinterface.exceptions.LoginFailureException;
import com.starbattle.client.testinterface.exceptions.NetworkTimeoutException;
import com.starbattle.client.testinterface.exceptions.WrongGUIElementException;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.chat.ChatContainer;
import com.starbattle.client.views.lobby.chat.ChatManager;
import com.starbattle.client.views.lobby.friends.FriendRelation;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.register.validate.PasswordHasher;
import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.GameWindow;
import com.starbattle.network.connection.objects.NP_Login;
import com.starbattle.network.connection.objects.NP_StartAnswer;

public class ClientAutomate {

	private ClientNetworkInterface clientNetwork;
	private GameWindow window;
	public static float toleranceSeconds = 5;
	public static float networkTimeout = 10;
	public static float stepDelay = 1f;

	public ClientAutomate(NetworkConnection connection, GameWindow window) {

		clientNetwork = new ClientNetworkInterface(connection);
		this.window = window;

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
		try {
			NP_StartAnswer answer = (NP_StartAnswer) waitForNetworkReceive(NP_StartAnswer.class);
			if (answer.openGame) {
				return;
			} else {
				throw new LoginFailureException(answer.errorMessage);
			}
		} catch (NetworkTimeoutException e) {
			e.printStackTrace();
			throw new LoginFailureException();
		}
	}

	public void clickButton(String buttonName) throws GUIElementNotFoundException, WrongGUIElementException {
		step();
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void fillInTextfield(String textfieldName, String text) throws GUIElementNotFoundException, WrongGUIElementException {
		step();
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
				if (viewID == window.getContent().getCurrentViewID()) {
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
	
	public Object waitForNetworkReceive(final Class<?> requestedObject) throws NetworkTimeoutException {
		return clientNetwork.waitForNetworkReceive(requestedObject);
	}

	private JComponent findGUI(String name) {
		ComponentFinder.findElement(name, window.getContent());
		return ComponentFinder.getFoundComponent();
	}

	private void step() {
		try {
			Thread.sleep((long) (1000f * stepDelay));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
