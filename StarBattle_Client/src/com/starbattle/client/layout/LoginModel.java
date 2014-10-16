package com.starbattle.client.layout;

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

import com.starbattle.client.resource.ResourceLoader;

public class LoginModel {

	private JTextField username=new JTextField(22);
	private JPasswordField password=new JPasswordField(22);
	private JPanel view=new JPanel();
	private JLabel errorText=new JLabel("  ");
	private JCheckBox rememberName=new JCheckBox("Remember Username",false);
	
	public LoginModel(String name, String pw)
	{
		username.setText(name);
		password.setText(pw);
		rememberName.setOpaque(false);
		rememberName.setForeground(new Color(200,200,200));
		password.setCaretColor(Color.WHITE);
		password.setOpaque(false);
		password.setForeground(Color.WHITE);
		username.setOpaque(false);
		username.setForeground(new Color(200,250,200));
		username.setCaretColor(Color.WHITE);
		errorText.setForeground(new Color(250,150,100));
		errorText.setFont(errorText.getFont().deriveFont(15f));
		view.setOpaque(false);
		view.setBorder(BorderFactory.createEmptyBorder(5, 75, 10, 0));
		view.setLayout(new VerticalLayout());
		view.add(errorText);
		view.add(createText("Username","user.png"));
		view.add(username);
		view.add(rememberName);
		view.add(Box.createVerticalStrut(5));
		view.add(createText("Password","key.png"));
		view.add(password);
		
	}
	
	private JLabel createText(String t, String iconName)
	{
		JLabel l=new JLabel(t,ResourceLoader.loadIcon(iconName),0);
		l.setFont(l.getFont().deriveFont(16f));
		l.setForeground(new Color(230,230,230));
		return l;
	}
	
	public JPanel getView() {
		return view;
	}
	
	public String getUserName()
	{
		return username.getText();
	}

	public void setErrorText(String error)
	{
		errorText.setText(error);
	}
	
	
	public String getPassword()
	{
		return hashPassword(password.getText());
	}
	
	private String hashPassword(String password) {
		String hash;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes(), 0, password.length());
			hash = new BigInteger(1, digest.digest()).toString(16);

			return hash;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("No MD5 hash...FATAL!");
			System.exit(1);
			return null;
		}
	}
	
	public void addKeyListener(KeyListener listener)
	{
		username.addKeyListener(listener);
		password.addKeyListener(listener);
	}
}
