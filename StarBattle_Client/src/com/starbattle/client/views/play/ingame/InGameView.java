package com.starbattle.client.views.play.ingame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.game.InGameClientControl;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.connection.objects.NP_ExitGame;

public class InGameView extends ContentView {

	public final static int VIEW_ID = 10;

	private DesignButton cancel = new DesignButton("Exit Game");

	public InGameView(final NetworkConnection connection, final InGameClientControl inGameClientControl) {

		view.setLayout(new BorderLayout());
	
		

		JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		center.setBackground(new Color(150, 150, 150));
		center.add(new DesignLabel("Game running!", Color.ORANGE, 25));
		view.add(center, BorderLayout.CENTER);

		/* center.add(cancel);
		
		DONT CLOSE FROM INSIDE => GAME CLIENT CANT START AGAIN
	
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// close game client
				inGameClientControl.closeGame();
				// send server notification
				NP_ExitGame exit = new NP_ExitGame();
				connection.getSendConnection().sendTCP(exit);
			}
		});*/
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
