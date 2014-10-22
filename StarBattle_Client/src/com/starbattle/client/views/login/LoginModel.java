package com.starbattle.client.views.login;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.StandardViewModel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.model.validate.PasswordHasher;
import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.resource.ResourceLoader;

public class LoginModel extends StandardViewModel {

	private JTextField username = new JTextField(19);
	private JPasswordField password = new JPasswordField(22);
	private JLabel errorText = new JLabel("  ");
	private JCheckBox rememberName = new JCheckBox("Remember Name", false);
	private JButton forgotPassword = new JButton("Forgot password?");

	public LoginModel() {
		rememberName.setOpaque(false);
		rememberName.setForeground(new Color(200, 200, 200));
		password.setCaretColor(Color.WHITE);
	    password.setOpaque(false);
		password.setForeground(Color.WHITE);
		username.setOpaque(false);
		username.setForeground(new Color(200, 200, 250));
		username.setFont(username.getFont().deriveFont(16f));
		username.setCaretColor(Color.WHITE);
		errorText.setForeground(new Color(250, 150, 100));
		errorText.setFont(errorText.getFont().deriveFont(15f));
		view.setOpaque(false);

		view.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 0));
		view.setLayout(new VerticalLayout(0, VerticalLayout.LEFT, VerticalLayout.BOTTOM));
		view.add(errorText);
		view.add(new DesignLabel("Accountname", "user.png"));
		view.add(username);
		view.add(rememberName);
		view.add(Box.createVerticalStrut(5));
		view.add(new DesignLabel("Password", "key.png"));
		view.add(password);
		forgotPassword.setContentAreaFilled(false);
		forgotPassword.setBorderPainted(false);
		forgotPassword.setForeground(new Color(100, 100, 250));
		view.add(forgotPassword);
	}

	public void setUsername(String name) {
		username.setText(name);
		rememberName.setSelected(true);
	}

	public void setForgotPasswordListener(ActionListener listener) {
		forgotPassword.addActionListener(listener);
	}

	public boolean isRememberUsername() {
		return rememberName.isSelected();
	}

	public String getUserName() {
		return username.getText();
	}

	public void setErrorText(String error) {
		errorText.setText(error);
	}

	public String getHashedPassword() {
		return PasswordHasher.hashPassword(new String(password.getPassword()));
	}

	public void addKeyListener(KeyListener listener) {
		username.addKeyListener(listener);
		password.addKeyListener(listener);
	}
}
