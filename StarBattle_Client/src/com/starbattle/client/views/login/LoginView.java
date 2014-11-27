package com.starbattle.client.views.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.connection.listener.NetworkRegistrationListener;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.resource.ClientConfiguration;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.client.views.reset.ResetPasswordView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_Login;

public class LoginView extends ContentView {

	public final static int VIEW_ID = 0;

	private JButton loginButton = new DesignButton("Login");
	private JButton registerButton = new DesignButton("Create Account");
	private LoginModel loginModel;
	private NetworkConnection networkConnection;
	private String rememberUsername = "login.rememberUsername";

	public LoginView(NetworkConnection connection) {

		windowSize = new Dimension(600, 600);

		this.networkConnection = connection;
		loginButton.setName("Button_Login");
		registerButton.setName("Button_Register");
		view.setCustomPaintInterface(new LoginBackgroundAnimation());
		view.setBackgroundImage(ResourceLoader.loadImage("backgroundScreen.jpg"));

		view.setLayout(new BorderLayout());

		loginModel = new LoginModel(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryLogin();
			}
		});

		JPanel footer = new JPanel();
		footer.setLayout(new FlowLayout(FlowLayout.LEFT));
		footer.setOpaque(false);
		footer.add(registerButton);
		footer.add(loginButton);

		JPanel block = new JPanel();
		block.setLayout(new BorderLayout());
		block.setOpaque(false);
		block.add(loginModel.getView(), BorderLayout.WEST);
		block.add(footer, BorderLayout.SOUTH);

		view.add(block, BorderLayout.WEST);

		// change to register view on button click
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindowView(RegisterView.VIEW_ID);
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryLogin();
			}
		});

		loginModel.setForgotPasswordListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindowView(ResetPasswordView.VIEW_ID);
			}
		});
	}

	private void tryLogin() {
		String name = loginModel.getUserName();
		String password = loginModel.getHashedPassword();

		// remember username if checked
		if (loginModel.isRememberUsername()) {
			ClientConfiguration.get().setProperty(rememberUsername, name);
		}

		NP_Login login = new NP_Login();
		login.playerName = name;
		login.password = password;
		networkConnection.getSendConnection().sendTCP(login);
	}

	private class Registration implements NetworkRegistrationListener {

		@Override
		public void registrationOk() {
			openView(LobbyView.VIEW_ID);
		}

		@Override
		public void registrationFailed(String error) {
			loginModel.setErrorText(error);
		}
	}

	@Override
	protected void initView() {

		networkConnection.setRegistrationListener(new Registration());
		loginModel.setErrorText(null);
		resizeWindow(windowSize);

		// check config for remember username
		String name = ClientConfiguration.get().getProperty(rememberUsername);
		if (name != null) {
			if (!name.isEmpty()) {
				loginModel.setUsername(name);
			}
		}
	}

	@Override
	protected void onClosing() {

		if (!loginModel.isRememberUsername()) {
			// remove properties value
			ClientConfiguration.get().remove(rememberUsername);
		}
	}

	@Override
	public int getViewID() {

		return VIEW_ID;
	}

}
