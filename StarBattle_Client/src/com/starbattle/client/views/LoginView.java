package com.starbattle.client.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.starbattle.client.window.ContentView;

public class LoginView extends ContentView {

	public final static int VIEW_ID = 0;

	
	private JButton loginButton=new JButton("Login");
	private JButton registerButton=new JButton("Create Account");
	private JTextField username=new JTextField(20);
	private JPasswordField password=new JPasswordField(20);
	
	public LoginView() {
		
		username.setText("TimoTester");
		password.setText("test123");
		
		view.setLayout(new BorderLayout());
		view.add(new JLabel("Login"),BorderLayout.NORTH);
		
		JPanel block=new JPanel();
		block.setLayout(new GridLayout(4, 1));
		block.add(new JLabel("Username"));
		block.add(username);
		block.add(new JLabel("Password"));
		block.add(password);
		view.add(block,BorderLayout.CENTER);
		
		JPanel footer=new JPanel();
		footer.setLayout(new FlowLayout());
		footer.add(registerButton);
		footer.add(loginButton);
		view.add(footer,BorderLayout.SOUTH);
		
		//change to register view on button click
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(RegisterView.VIEW_ID);
			}
		});
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {

		return VIEW_ID;
	}

}
