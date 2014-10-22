package com.starbattle.client.views.register;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.starbattle.client.layout.StandardViewModel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.resource.ResourceLoader;

public class RegisterModel extends StandardViewModel {

	private JTextField accountname = new JTextField(22);
	private JTextField displayname = new JTextField(22);
	private JTextField email = new JTextField(22);
	private JPasswordField password = new JPasswordField(22);
	private JPasswordField password2 = new JPasswordField(22);
	private JLabel errorText = new JLabel("  ");

	public RegisterModel() {
		errorText.setForeground(new Color(250, 50, 50));
		errorText.setFont(errorText.getFont().deriveFont(15f));
		view.setBackground(new Color(200,200,200));
		view.setLayout(new VerticalLayout());
		view.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 50));
		view.add(createText("Accountname", "user.png"));
		view.add(accountname);
		addInfo("The secret name for your account registration.");
		view.add(Box.createVerticalStrut(5));
		view.add(createText("Displayname", "comment.png"));
		view.add(displayname);
		addInfo("Public name of your player, can be changed later. ");	
		view.add(Box.createVerticalStrut(5));	
		view.add(createText("Password", "key.png"));
		view.add(password);
		addInfo("Password restrictions: at least 8 characters, mixed upper and lower case and at least one special symbol!");
		view.add(Box.createVerticalStrut(5));
		view.add(createText("Repeat Password ", "key.png"));
		view.add(password2);
		addInfo("Repeat password to prevent misspelling");
		view.add(Box.createVerticalStrut(5));
		view.add(createText("EMail", "email.png"));
		view.add(email);
		addInfo("You need a valid email for administration");
		view.add(Box.createVerticalStrut(5));
		view.add(errorText);

	}
	
	private void addInfo(String text)
	{
		JTextArea info=new JTextArea(0, 25);
		info.setOpaque(false);
		info.setText(text);
		info.setLineWrap(true);
		info.setEditable(false);
		info.setWrapStyleWord(true);
		view.add(info);
	}

	private JLabel createText(String t, String iconName) {
		JLabel l = new JLabel(t, ResourceLoader.loadIcon(iconName), 0);
		l.setFont(l.getFont().deriveFont(14f));
		l.setForeground(new Color(50, 50, 50));
		return l;
	}

	public String getAccountName() {
		return accountname.getText();
	}
	
	public String getDisplayName()
	{
		return displayname.getText();
	}

	public void setErrorText(String error) {
		errorText.setText(error);
	}

	public String getEmail() {
		return email.getText();
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	public String getRepeatedPassword() {
		return new String(password2.getPassword());
	}

	public void addKeyListener(KeyListener listener) {
		accountname.addKeyListener(listener);
		password.addKeyListener(listener);
		password2.addKeyListener(listener);
		email.addKeyListener(listener);
	}
}
