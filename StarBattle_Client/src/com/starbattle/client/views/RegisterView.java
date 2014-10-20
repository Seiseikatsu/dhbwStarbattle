package com.starbattle.client.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.model.RegisterModel;
import com.starbattle.client.window.ContentView;

public class RegisterView extends ContentView{

	public final static int VIEW_ID = 1;

	private JButton backButton=new JButton("Back");
	private JButton registerButton=new JButton("Register");	
	private RegisterModel registerModel=new RegisterModel();
	
	public  RegisterView(NetworkConnection connection) {
		
		view.setLayout(new BorderLayout());
		JLabel title=new JLabel("Register",JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(20f));
		
		view.add(title,BorderLayout.NORTH);
		view.add(registerModel.getView(),BorderLayout.CENTER);
		
		JPanel footer=new JPanel();
		footer.add(backButton);
		footer.add(registerButton);
		view.add(footer,BorderLayout.SOUTH);
		
		
		//go back to login view on back button click
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(LoginView.VIEW_ID);
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
