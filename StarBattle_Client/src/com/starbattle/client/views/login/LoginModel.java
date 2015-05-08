package com.starbattle.client.views.login;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.DesignTextField;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.views.register.validate.PasswordHasher;

public class LoginModel extends ViewModel {

	private JTextField username;
	private JPasswordField password = new JPasswordField(22);
	private JLabel errorText = new DesignLabel(null,"error.png",new Color(250, 100, 100));
	private JCheckBox rememberName = new JCheckBox("Remember Name", false);
	private JButton forgotPassword = new JButton("Forgot password?");

	public LoginModel(final ActionListener textfieldListener) {
		
		username=new DesignTextField(19, 16f,textfieldListener);
		
		//debug
	//	username.setText("TimoTester");
		//password.setText("Timotest#1");
		
		username.setName("Login_Name");
		password.setName("Login_Password");
		errorText.setOpaque(true);
		errorText.setBackground(new Color(0,0,0));
		rememberName.setFocusable(false);
		rememberName.setOpaque(false);
		rememberName.setForeground(new Color(200, 200, 200));
		password.setCaretColor(Color.WHITE);
	    password.setOpaque(false);
		password.setForeground(Color.WHITE);
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
		
		password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textfieldListener.actionPerformed(null);
				}
			}
		});
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
}
