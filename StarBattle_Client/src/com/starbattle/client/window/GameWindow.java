package com.starbattle.client.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow {

	private JFrame window;
	private WindowContent content;

	public GameWindow(Dimension size, String name) {
		window = new JFrame(name);
		updateSize(size);
		content = new WindowContent(this);
		window.setContentPane(content);
		window.setResizable(false);
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

}
