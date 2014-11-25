package com.starbattle.client.testinterface.tester;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkCommunctionListener;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.friends.FriendRelation;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.register.validate.PasswordHasher;
import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.GameWindow;
import com.starbattle.network.connection.objects.NP_Login;
import com.starbattle.network.connection.objects.NP_StartAnswer;

public class ClientAutomate {

	private NetworkConnection connection;
	private GameWindow window;
	public static float toleranceSeconds = 5;
	public static float networkTimeout = 10;
	public static float stepDelay = 1f;
	private Object networkObject;

	public ClientAutomate(NetworkConnection connection, GameWindow window) {
		this.connection = connection;
		this.window = window;
		connection.setNetworkCommunctionListener(new NetworkCommunctionListener() {

			@Override
			public void received(Object object) {
				networkObject = object;
			}
		});
	}

	public void doLogin(String accountName, String password) throws LoginFailureException {
		step();
		setInView(LoginView.VIEW_ID);
		String pw = PasswordHasher.hashPassword(password);
		NP_Login login = new NP_Login();
		login.password = pw;
		login.playerName = accountName;
		connection.getSendConnection().sendTCP(login);
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

	public void click(String buttonName) throws GUIElementNotFoundException, WrongGUIElementException {
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

	public void fillIn(String textfieldName, String text) throws GUIElementNotFoundException, WrongGUIElementException {
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
				ContentView view = window.getContent().getViews().get(LobbyView.VIEW_ID);
				LobbyView lobby = (LobbyView) view;
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
				ContentView view = window.getContent().getViews().get(LobbyView.VIEW_ID);
				LobbyView lobby = (LobbyView) view;
				FriendRelation relation = lobby.getFriendPanel().getFriendRelationTo(friend);
				if (relation != null) {
					return true;
				}
				return false;
			}
		});
		return check.isCheckOk();
	}

	public Object waitForNetworkReceive(final Class<?> requestedObject) throws NetworkTimeoutException {
		NetworkCheck check = new NetworkCheck(new ToleranceCheckTask() {
			public boolean check() {

				if (networkObject != null) {
					if (networkObject.getClass().equals(requestedObject)) {
						return true;
					}
				}
				return false;
			}
		});
		if (networkObject == null) {
			throw new NetworkTimeoutException();
		}
		if (!check.isCheckOk()) {
			throw new NetworkTimeoutException();
		}
		Object o = networkObject;
		networkObject = null;
		return o;
	}

	private JComponent findGUI(String name) {
		foundComponent = null;
		findElement(name, window.getContent());
		return foundComponent;
	}

	private JComponent foundComponent;

	private void findElement(String name, Container c) {
		Component[] components = c.getComponents();

		for (Component com : components) {
			String cn = com.getName();

			if (cn != null) {
				if (cn.equals(name)) {
					foundComponent = (JComponent) com;
				}
			}
			if (com instanceof Container) {
				findElement(name, (Container) com);
			}
		}
	}

	private void step() {
		try {
			Thread.sleep((long) (1000f * stepDelay));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
