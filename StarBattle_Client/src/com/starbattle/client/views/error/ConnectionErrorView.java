package com.starbattle.client.views.error;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.main.error.ConnectionErrorListener;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.window.ContentView;

public class ConnectionErrorView extends ContentView {

	public final static int VIEW_ID = -1;

	private Dimension windowSize = new Dimension(200, 200);
	private ConnectionErrorListener connectionErrorListener;

	private JButton close = new JButton("Close");
	private JButton reconnect = new JButton("Reconnect");

	public ConnectionErrorView(ConnectionErrorListener connectionErrorListener) {
		this.connectionErrorListener = connectionErrorListener;
		initLayout();
	}

	private void initLayout() {
		view.setLayout(new BorderLayout());
		JLabel title=new JLabel("Connection Error");
		title.setFont(title.getFont().deriveFont(20f));
		view.add(title,BorderLayout.NORTH);
		view.add(new JLabel(ResourceLoader.loadIcon("disconnect_icon.png"), 0));

		JPanel bot = new JPanel();
		bot.add(reconnect);
		bot.add(close);
		view.add(bot, BorderLayout.SOUTH);

		reconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionErrorListener.tryReconnect();
			}
		});

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionErrorListener.exit();
			}
		});

	}

	@Override
	protected void initView() {
		resizeWindow(windowSize);
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
