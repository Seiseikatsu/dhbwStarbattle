package com.starbattle.client.model;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.resource.ResourceLoader;

public class RegisterModel {

	private JTextField username = new JTextField(22);
	private JTextField email = new JTextField(22);
	private JPasswordField password = new JPasswordField(22);
	private JPasswordField password2 = new JPasswordField(22);
	private JPanel view = new JPanel();
	private JLabel errorText = new JLabel("  ");

	public RegisterModel() {
		errorText.setForeground(new Color(250, 50, 50));
		errorText.setFont(errorText.getFont().deriveFont(15f));
		view.setLayout(new VerticalLayout());
		view.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 50));
		view.add(createText("Username", "user.png"));
		view.add(username);
		view.add(Box.createVerticalStrut(5));
		view.add(createText("Password", "key.png"));
		view.add(password);
		view.add(Box.createVerticalStrut(5));
		view.add(createText("Repeat Password ", "key.png"));
		view.add(password2);
		view.add(Box.createVerticalStrut(5));
		view.add(createText("EMail", "email.png"));
		view.add(email);
		view.add(Box.createVerticalStrut(5));
		view.add(errorText);
		
	}

	private JLabel createText(String t, String iconName) {
		JLabel l = new JLabel(t, ResourceLoader.loadIcon(iconName), 0);
		l.setFont(l.getFont().deriveFont(14f));
		l.setForeground(new Color(50,50,50));
		return l;
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
	
	public String getEmail()
	{
		return email.getText();
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	public String getRepeatedPassword() {
		return new String(password2.getPassword());
	}

	public void addKeyListener(KeyListener listener) {
		username.addKeyListener(listener);
		password.addKeyListener(listener);
		password2.addKeyListener(listener);
		email.addKeyListener(listener);
	}
}
