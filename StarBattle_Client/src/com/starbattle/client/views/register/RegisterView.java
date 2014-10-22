package com.starbattle.client.views.register;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.RegistrationListener;
import com.starbattle.client.model.validate.PasswordChecker;
import com.starbattle.client.model.validate.PasswordHasher;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_Register;

public class RegisterView extends ContentView {

	public final static int VIEW_ID = 1;
	
	private Dimension windowSize=new Dimension(400,500);
	
	private JButton backButton = new JButton("Back");
	private JButton registerButton = new JButton("Register");
	private RegisterModel registerModel = new RegisterModel();
	private SendServerConnection sendConnection;

	public RegisterView(NetworkConnection connection) {

		connection.setRegistrationListener(new Registration());
		sendConnection = connection.getSendConnection();

		view.setLayout(new BorderLayout());
		JLabel title = new JLabel("Register new Account", JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(20f));

		view.add(title, BorderLayout.NORTH);
		view.add(registerModel.getView(), BorderLayout.CENTER);

		JPanel footer = new JPanel();
		footer.add(backButton);
		footer.add(registerButton);
		view.add(footer, BorderLayout.SOUTH);

		registerModel.addKeyListener(new KeyEnter());
		// go back to login view on back button click
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(LoginView.VIEW_ID);
			}
		});
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryRegister();
			}
		});
	}

	private void tryRegister() {
		// check if password valid
		String password = registerModel.getPassword();
		if (password.equals(registerModel.getRepeatedPassword())) {
			if (PasswordChecker.isValid(password)) {
				// send register object
				NP_Register reg = new NP_Register();
				reg.email = registerModel.getEmail();
				reg.password = PasswordHasher.hashPassword(password);
				reg.accountName = registerModel.getAccountName();
				reg.displayName= registerModel.getDisplayName();
				sendConnection.sendTCP(reg);
			} else {
				registerModel.setErrorText("Invalid password!");
			}
		} else {
			registerModel.setErrorText("Passwords dont match!");
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

	private class Registration implements RegistrationListener {

		@Override
		public void registrationOk() {
			openView(LobbyView.VIEW_ID); // login after register
		}

		@Override
		public void registrationFailed(String error) {
			registerModel.setErrorText(error);
		}
	}

	private class KeyEnter implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				tryRegister();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

}
