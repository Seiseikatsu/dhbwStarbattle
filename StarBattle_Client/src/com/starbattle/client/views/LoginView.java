package com.starbattle.client.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.RegistrationListener;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_Login;

public class LoginView extends ContentView {

	public final static int VIEW_ID = 0;
	private Dimension windowSize=new Dimension(400,400);
	
	private JButton loginButton = new JButton("Login");
	private JButton registerButton = new JButton("Create Account");
	private JTextField username = new JTextField(20);
	private JPasswordField password = new JPasswordField(20);
	private JLabel errorText = new JLabel("", JLabel.CENTER);
	private SendServerConnection sendConnection;

	public LoginView(NetworkConnection connection) {

		sendConnection = connection.getSendConnection();
		connection.setRegistrationListener(new Registration());
		username.setText("TimoTester");
		password.setText("test123");

		errorText.setForeground(new Color(200, 0, 0));
		view.setLayout(new BorderLayout());
		JLabel title = new JLabel("Login", JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(20f));
		view.add(title, BorderLayout.NORTH);

		JPanel block = new JPanel();
		block.setLayout(new GridLayout(5, 1));
		block.add(new JLabel("Username"));
		block.add(username);
		block.add(new JLabel("Password"));
		block.add(password);
		block.add(errorText);
		view.add(block, BorderLayout.CENTER);

		KeyEnter keyEnter = new KeyEnter();
		username.addKeyListener(keyEnter);
		password.addKeyListener(keyEnter);

		JPanel footer = new JPanel();
		footer.setLayout(new FlowLayout());
		footer.add(registerButton);
		footer.add(loginButton);
		view.add(footer, BorderLayout.SOUTH);

		// change to register view on button click
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(RegisterView.VIEW_ID);
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryLogin();
			}
		});
	}

	private void tryLogin() {
		String name = username.getText();
		String password = this.password.getText();

		NP_Login login = new NP_Login();
		login.playerName = name;
		login.password = password;
		sendConnection.sendTCP(login);
	}

	private class Registration implements RegistrationListener {

		@Override
		public void registrationOk() {
			// TODO Auto-generated method stub
			openView(GameView.VIEW_ID);
		}

		@Override
		public void registrationFailed(String error) {
			// TODO Auto-generated method stub
			errorText.setText(error);
		}
	}

	private class KeyEnter implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				tryLogin();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

	@Override
	protected void initView() {
		resizeWindow(windowSize);
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {

		return VIEW_ID;
	}

}
