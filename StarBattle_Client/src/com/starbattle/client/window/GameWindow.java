package com.starbattle.client.window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import com.starbattle.client.resource.ClientConfiguration;

public class GameWindow {

	private JFrame window;
	private WindowContent content;

	public GameWindow(Dimension size, String name) {
		window = new JFrame(name);
		if (size != null) {
			updateSize(size);
		}
		content = new WindowContent(this);
		window.setContentPane(content);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				content.isClosing();
				ClientConfiguration.saveProperties();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
	}

	public void updateSize(Dimension size) {
		window.setSize(size);
		centerWindow();
	}

	public void addView(ContentView view) {
		content.createView(view);
	}

	public void open(int startViewID) {
		content.showView(startViewID);
		window.setVisible(true);
	}

	public void close() {
		window.setVisible(false);
		window.dispose();
	}

	private void centerWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = window.getSize();
		int x = screenSize.width / 2 - windowSize.width / 2;
		int y = screenSize.height / 2 - windowSize.height / 2 - 40;
		window.setLocation(x, y);
	}

	public JFrame getWindow() {
		return window;
	}
}
