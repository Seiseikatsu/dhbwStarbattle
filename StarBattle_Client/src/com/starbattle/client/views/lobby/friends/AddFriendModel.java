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

	private JTextField friendname = new JTextField(22);
	private DesignLabel error=new DesignLabel("", Color.RED,12);

	public AddFriendModel() {

		friendname.setName("FriendUsername");
		view.setLayout(new VerticalLayout());
		view.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 50));
		view.setBackground(new Color(200, 200, 200));

		view.add(new DesignLabel("Friend Name", ResourceLoader.loadIcon("user.png"),new Color(50,50,50),16));
		view.add(friendname);
		view.add(Box.createVerticalStrut(5));
		view.add(Box.createVerticalStrut(50));
		view.add(error);

	}

	public void setErrorText(String text)
	{
		error.setText(text);
	}

	public String getFriendName() {
		return friendname.getText();
	}


}
