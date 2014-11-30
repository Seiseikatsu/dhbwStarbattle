package com.starbattle.client.window;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class ModalWindowViewer {

	private boolean isOpen;
	private JDialog dialog;
	private JFrame frame;
	private ContentView currentView;

	public ModalWindowViewer(JFrame jFrame) {
		this.frame = jFrame;
		dialog = new JDialog(jFrame);
		dialog.setUndecorated(true);
		dialog.setResizable(false);
		dialog.setModal(false);
		dialog.setTitle(null);

	}

	private void placeWindow(int w, int h) {
		int x = frame.getX() + frame.getWidth() / 2 - w / 2;
		int y = frame.getY() + frame.getHeight() / 2 - h / 2;
		dialog.setLocation(x, y);
	}

	public void open(ContentView view) {

		currentView = view;
		dialog.setContentPane(view.getView());
		dialog.setSize(view.windowSize);
		placeWindow(view.getWindowSize().width, view.getWindowSize().height);
		dialog.setVisible(true);
		isOpen = true;

	}

	public void close() {
		if(currentView!=null)
		{
		currentView.onClosing();
		}
		dialog.dispose();
		isOpen = false;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public ContentView getCurrentView() {
		return currentView;
	}

}
