package com.starbattle.client.views.play.queue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.views.play.PlayView;
import com.starbattle.client.window.ContentView;

public class WaitingView extends ContentView {

	public final static int VIEW_ID = 9;

	private WaitingBackground background = new WaitingBackground();
	private DesignButton cancel = new DesignButton("Cancel");
	private CancelSearchListener cancelSearchListener;

	public WaitingView(NetworkConnection connection) {
		view.setLayout(new BorderLayout());
		view.add(background.getView(), BorderLayout.CENTER);
		cancel.setButtonStyle(2);

		JPanel bottom=new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom.add(cancel);
		view.add(bottom, BorderLayout.SOUTH);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(PlayView.VIEW_ID);
				cancelSearchListener.cancel();
			}
		});

	}

	public void setCancelSearchListener(CancelSearchListener cancelSearchListener) {
		this.cancelSearchListener = cancelSearchListener;
	}

	@Override
	protected void initView() {
	}

	public void setSearchSettings(String mode, String map) {
		background.init(mode, map);
	}

	@Override
	protected void onClosing() {
	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
