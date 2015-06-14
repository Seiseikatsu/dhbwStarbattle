package com.starbattle.client.window;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowContent extends JPanel {

	private HashMap<Integer, ContentView> views = new HashMap<Integer, ContentView>();
	private ContentViewChanger contentViewChanger = new ContentViewChanger();
	private GameWindow window;
	private ContentView currentView;
	private ContentView lastNonwindowView;
	private ModalWindowViewer modalWindowViewer;

	public WindowContent(GameWindow window) {
		modalWindowViewer = new ModalWindowViewer( window.getWindow());
		DesignWindowBorder border = new DesignWindowBorder(window.getWindow().getTitle());
		this.setBorder(border);
		this.window = window;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		BorderMouseListener borderMouseListener = new BorderMouseListener(border, new WindowListener());
		this.addMouseListener(borderMouseListener);
		this.addMouseMotionListener(borderMouseListener);

	}

	public void closeWindows() {
		modalWindowViewer.close();
	}

	public int getCurrentViewID() {
		return currentView.getViewID();
	}

	public void createView(ContentView view) {
		int id = view.getViewID();
		System.out.println("Added view : "+id+"  "+view.getClass().getSimpleName());
		view.setViewChangeListener(contentViewChanger);
		views.put(id, view);
	}

	public void isClosing() {
		currentView.onClosing();
	}

	public void showView(int id) {
		if (modalWindowViewer.isOpen()) {
			modalWindowViewer.close();
		}

		try {
			lastNonwindowView = views.get(id);
			showView(views.get(id));
			this.repaint();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void showWindowView(int newViewID) {
		if (modalWindowViewer.isOpen()) {
			modalWindowViewer.close();
		}
		ContentView view = views.get(newViewID);
		currentView = view;
		view.initView();
		modalWindowViewer.open(view);
	}

	public void showView(ContentView view) {
		currentView = view;
		System.out.println("Show View "+view.getClass().getSimpleName());
		view.open();
		this.removeAll();
		this.add(view.getView(), BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	private class ContentViewChanger implements ViewChangeListener {

		@Override
		public void openView(int newViewID) {
			showView(newViewID);
		}

		@Override
		public void resizeWindow(Dimension newSize) {
			if (!modalWindowViewer.isOpen()) {
				window.updateSize(newSize);
			}
		}

		@Override
		public void openWindowView(int newViewID) {
			showWindowView(newViewID);
		}

	}

	private class WindowListener implements WindowBorderListener {

		@Override
		public void closeWindow() {
			JFrame frame = window.getWindow();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}

		@Override
		public void setLocation(int x, int y) {
			JFrame frame = window.getWindow();
			frame.setLocation(x, y);
		}

	}

	public ContentView getLastNonwindowView() {
		return lastNonwindowView;
	}

	public HashMap<Integer, ContentView> getViews() {
		return views;
	}

	public ModalWindowViewer getModalWindowViewer() {
		return modalWindowViewer;
	}
}
