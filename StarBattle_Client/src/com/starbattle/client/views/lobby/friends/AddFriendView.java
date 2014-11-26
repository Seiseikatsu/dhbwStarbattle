package com.starbattle.client.views.lobby.friends;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_FriendRequest;
import com.starbattle.network.connection.objects.NP_ResetEmail;

public class AddFriendView extends ContentView {

	public final static int VIEW_ID = 5;

	
	private JButton backButton = new DesignButton("Back");
	private JButton addFriendButton = new DesignButton("Send Request");
	private SendServerConnection sendConnection;
	private AddFriendModel model=new AddFriendModel();

	public AddFriendView(NetworkConnection connection) {

		windowSize=new Dimension(400,250);
		
		sendConnection = connection.getSendConnection();
		view.setLayout(new BorderLayout());
		view.setBorder(BorderFactory.createLineBorder(new Color(100,50,10),3));
		view.setBackground(new Color(170,110,40));
		
		JLabel title = new DesignLabel("Send Friend Request",25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(20f));
		view.add(title, BorderLayout.NORTH);
		view.add(model.getView(), BorderLayout.CENTER);

		JPanel footer = new JPanel();
		footer.setOpaque(false);
		footer.add(backButton);
		footer.add(addFriendButton);
		view.add(footer, BorderLayout.SOUTH);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(LobbyView.VIEW_ID);
			}
		});

		addFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFriend();
			}
		});
	}

	private void addFriend() {
	//Send Friend Request	
	String friendName=model.getFriendName();
	NP_FriendRequest request=new NP_FriendRequest();
	request.inputName=friendName;
	sendConnection.sendTCP(request);
	//close window
	openView(LobbyView.VIEW_ID);
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
