package com.starbattle.client.model;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.model.validate.PasswordHasher;
import com.starbattle.client.resource.ResourceLoader;

public class LoginModel {

	private JTextField username = new JTextField(22);
	private JPasswordField password = new JPasswordField(22);
	private JPanel view = new JPanel();
	private JLabel errorText = new JLabel("  ");
	private JCheckBox rememberName = new JCheckBox("Remember Username", false);
	private JButton forgotPassword=new JButton("Forgot password?");
	
	public LoginModel(String name, String pw) {
		username.setText(name);
		password.setText(pw);
		rememberName.setOpaque(false);
		rememberName.setForeground(new Color(200, 200, 200));
		password.setCaretColor(Color.WHITE);
		password.setOpaque(false);
		password.setForeground(Color.WHITE);
		username.setOpaque(false);
		username.setForeground(new Color(200, 250, 200));
		username.setCaretColor(Color.WHITE);
		errorText.setForeground(new Color(250, 150, 100));
		errorText.setFont(errorText.getFont().deriveFont(15f));
		view.setOpaque(false);
		view.setBorder(BorderFactory.createEmptyBorder(5, 75, 10, 0));
		view.setLayout(new VerticalLayout());
		view.add(errorText);
		view.add(createText("Username", "user.png"));
		view.add(username);
		view.add(rememberName);
		view.add(Box.createVerticalStrut(5));
		view.add(createText("Password", "key.png"));
		view.add(password);
		forgotPassword.setContentAreaFilled(false);
		forgotPassword.setBorderPainted(false);
		forgotPassword.setForeground(new Color(100,100,250));
		view.add(forgotPassword);
	}

	private JLabel createText(String t, String iconName) {
		JLabel l = new JLabel(t, ResourceLoader.loadIcon(iconName), 0);
		l.setFont(l.getFont().deriveFont(16f));
		l.setForeground(new Color(230, 230, 230));
		return l;
	}

	
	public void setForgotPasswordListener(ActionListener listener)
	{
		forgotPassword.addActionListener(listener);
	}
	
	public JPanel getView() {
		return view;
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
