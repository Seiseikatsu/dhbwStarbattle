package com.starbattle.client.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.starbattle.client.window.ContentView;

public class RegisterView extends ContentView{

	public final static int VIEW_ID = 1;

	private JButton backButton=new JButton("Back");
	
	public  RegisterView() {
		// TODO Auto-generated constructor stub
		
		view.setLayout(new BorderLayout());
		view.add(new JLabel("Create new Account"),BorderLayout.NORTH);
		
		view.add(backButton,BorderLayout.SOUTH);
		
		//go back to login view on back button click
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(LoginView.VIEW_ID);
			}
		});
	}
	
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onClosing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getViewID() {
		// TODO Auto-generated method stub
		return VIEW_ID;
	}

	
	
}
