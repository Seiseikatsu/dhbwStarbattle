package com.starbattle.client.views.lobby.friends;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class AddFriendModel extends ViewModel {

	private JTextField username = new JTextField(22);
	private JTextArea info = new JTextArea(5, 22);

	public AddFriendModel() {

		view.setLayout(new VerticalLayout());
		view.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 50));
		view.setBackground(new Color(200, 200, 200));

		view.add(new DesignLabel("Friend Name", ResourceLoader.loadIcon("user.png"),new Color(50,50,50),16));
		view.add(username);
		view.add(Box.createVerticalStrut(5));
		view.add(Box.createVerticalStrut(50));
		view.add(info);
		info.setEditable(false);
		info.setOpaque(false);
		info.append("New Friend Request: \n");
		info.append(" \n");

	}


	public String getUserName() {
		return username.getText();
	}


}
