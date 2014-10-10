package com.starbattle.mapeditor.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowContainer {

	private JFrame window;

	public WindowContainer(Dimension size, String name) {
		window = new JFrame(name);
		updateSize(size);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void open()
	{
		window.setVisible(true);
	}

	public void updateSize(Dimension size) {
		window.setSize(size);
		centerWindow();
	}

	public void setView(ContentPanel panel) {
		window.setContentPane(panel.getView());
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
