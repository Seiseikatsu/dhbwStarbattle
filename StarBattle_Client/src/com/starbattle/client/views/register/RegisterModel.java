package com.starbattle.client.views.register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.views.register.validate.NameGenerator;

public class RegisterModel extends ViewModel {

	private JTextField accountname = new JTextField(22);
	private JTextField displayname = new JTextField(22);
	private JTextField email = new JTextField(22);
	private JPasswordField password = new JPasswordField(22);
	private JPasswordField password2 = new JPasswordField(22);
	private JButton generateName=new DesignButton(ResourceLoader.loadIcon("dice.png",24,24));
	private JLabel errorText = new DesignLabel(null,"error.png",new Color(150, 50, 50));
	private Color labelColor=new Color(50, 50, 50);
	
	public RegisterModel() {
		
		accountname.setName("Register_Accountname");
		displayname.setName("Register_Displayname");
		email.setName("Register_Email");
		password.setName("Register_Password");
		password2.setName("Register_Repeat_Password");
		errorText.setName("Error_Text");
		
		view.setBackground(new Color(200,200,200));
		view.setLayout(new VerticalLayout());
		view.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 50));
		view.add(new DesignLabel("Accountname", "user.png",labelColor));
		view.add(accountname);
		addInfo("The secret name for your account registration.");
		view.add(Box.createVerticalStrut(5));
		view.add(new DesignLabel("Displayname", "comment.png",labelColor));
		JPanel namePanel=new JPanel();
		namePanel.setOpaque(false);
		namePanel.add(displayname);
		namePanel.add(generateName);
		view.add(namePanel);
		addInfo("Public name of your player, can be changed later. ");	
		view.add(Box.createVerticalStrut(5));	
		view.add(new DesignLabel("Password", "key.png",labelColor));
		view.add(password);
		addInfo("Password restrictions: at least 8 characters, mixed upper and lower case and at least one special symbol!");
		view.add(Box.createVerticalStrut(5));
		view.add(new DesignLabel("Repeat Password ", "key.png",labelColor));
		view.add(password2);
		addInfo("Repeat password to prevent misspelling");
		view.add(Box.createVerticalStrut(5));
		view.add(new DesignLabel("E-Mail", "email.png",labelColor));
		view.add(email);
		addInfo("You need a valid email for administration");
		view.add(Box.createVerticalStrut(5));
		view.add(errorText);

		generateName.setToolTipText("Ask the universe for a name!");
		generateName.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayname.setText(NameGenerator.generateRandomName());
			}
		});
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
