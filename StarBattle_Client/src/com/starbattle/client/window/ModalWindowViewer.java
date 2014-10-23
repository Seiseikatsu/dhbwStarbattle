package com.starbattle.client.window;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ModalWindowViewer {

	private boolean isOpen;
	private JDialog dialog;
	private JFrame frame;

	public ModalWindowViewer(JFrame jFrame) {
		this.frame = jFrame;
		dialog = new JDialog(jFrame);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setTitle(null);
	}

	private void placeWindow(int w, int h) {
		int x = frame.getX() + frame.getWidth() / 2 - w / 2;
		int y = frame.getY() + frame.getHeight() / 2 - h / 2;
		dialog.setLocation(x, y);
	}

	public void open(ContentView view) {
		isOpen = true;
		dialog.setContentPane(view.getView());
		dialog.setSize(view.windowSize);
		placeWindow(view.getWindowSize().width, view.getWindowSize().height);
		dialog.setVisible(true);
	}

	public void close() {
		dialog.dispose();
		isOpen = false;
	}

	public boolean isOpen() {
		return isOpen;
	}

}
