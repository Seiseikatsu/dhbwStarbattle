package com.starbattle.client.views.register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.RegistrationListener;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.register.validate.PasswordChecker;
import com.starbattle.client.views.register.validate.PasswordHasher;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_Register;

public class RegisterView extends ContentView {

	public final static int VIEW_ID = 1;

	
	private JButton backButton = new DesignButton("Back");
	private JButton registerButton = new DesignButton("Register");
	private RegisterModel registerModel = new RegisterModel();
	private NetworkConnection networkConnection;
	
	public RegisterView(NetworkConnection connection) {

		windowSize=new Dimension(400,500);
		this.networkConnection=connection;
		
		view.setBorder(BorderFactory.createLineBorder(new Color(100,50,10),3));
		view.setBackground(new Color(170,110,40));
		view.setLayout(new BorderLayout());
		DesignLabel title=new DesignLabel("Register new Account", 25);
		title.setHorizontalAlignment(JLabel.CENTER);
		view.add(title, BorderLayout.NORTH);
		view.add(registerModel.getView(), BorderLayout.CENTER);

		JPanel footer = new JPanel();
		footer.setOpaque(false);
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
				networkConnection.getSendConnection().sendTCP(reg);
			} else {
				registerModel.setErrorText("Invalid password!");
			}
		} else {
			registerModel.setErrorText("Passwords dont match!");
		}
	}

	@Override
	protected void initView() {
		
		networkConnection.setRegistrationListener(new Registration());
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
