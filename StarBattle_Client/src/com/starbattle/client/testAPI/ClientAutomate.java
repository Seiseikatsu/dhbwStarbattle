package com.starbattle.client.testAPI;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.lobby.friends.FriendRelation;
import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.GameWindow;
import com.starbattle.network.server.PlayerConnection;

public class ClientAutomate {

	private NetworkConnection connection;
	private GameWindow window;
	public static float toleranceSeconds = 5;
	private long lastMilli;

	public ClientAutomate(NetworkConnection connection, GameWindow window) {
		this.connection = connection;
		this.window = window;
	}

	public void click(String buttonName) throws GUIElementNotFoundException, WrongGUIElementException {
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

	public boolean isInScene(final int sceneID) {
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check() {
				if(sceneID==window.getContent().getCurrentViewID())
				{
					return true;
				}
				return false;
			}
		});
		return check.isCheckOk();
	}
	
	public boolean friendRelationStateIs(final String friend,final int relationType)
	{
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check() {
				ContentView view = window.getContent().getViews().get(LobbyView.VIEW_ID);
				LobbyView lobby=(LobbyView)view;
				FriendRelation relation = lobby.getFriendPanel().getFriendRelationTo(friend);
				if(relation!=null)
				{
					if(relationType==relation.getState())
					{
						return true;
					}
				}
				return false;
			}
		});
		return check.isCheckOk();		
	}
	
	public boolean hasFriendRelation(final String friend)
	{
		ToleranceCheck check = new ToleranceCheck(new ToleranceCheckTask() {
			public boolean check() {
				ContentView view = window.getContent().getViews().get(LobbyView.VIEW_ID);
				LobbyView lobby=(LobbyView)view;
				FriendRelation relation = lobby.getFriendPanel().getFriendRelationTo(friend);
				if(relation!=null)
				{
					return true;
				}
				return false;
			}
		});
		return check.isCheckOk();		
	}

	private JComponent findGUI(String name) {
		return findElement(name, window.getContent());
	}

	private JComponent findElement(String name, JComponent component) {
		if (component.getName().equals(name)) {
			return component;
		} else {
			for (Component c : component.getComponents()) {
				if (c != null) {
					return findElement(name, (JComponent) c);
				}
			}
		}
		return null;
	}

}
