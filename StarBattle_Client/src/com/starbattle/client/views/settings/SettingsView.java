package com.starbattle.client.views.settings;

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
import com.starbattle.client.window.ContentView;

public class SettingsView extends ContentView{

	public final static int VIEW_ID=8;
	private JButton backButton = new DesignButton("Back");

	public  SettingsView(NetworkConnection networkConnection) {
		windowSize = new Dimension(500, 400);
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout());
		view.setBorder(BorderFactory.createLineBorder(new Color(100,50,10),3));
		view.setBackground(new Color(170,110,40));
		
		JLabel title = new DesignLabel("Game Settings",25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(20f));
		view.add(title, BorderLayout.NORTH);
	
		JPanel footer = new JPanel();
		footer.setOpaque(false);
		footer.add(backButton);
		view.add(footer, BorderLayout.SOUTH);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(LobbyView.VIEW_ID);
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
