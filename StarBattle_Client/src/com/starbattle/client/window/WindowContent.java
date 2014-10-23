package com.starbattle.client.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowContent extends JPanel{

	
	private HashMap<Integer,ContentView> views=new HashMap<Integer,ContentView>();
	private ContentViewChanger contentViewChanger=new ContentViewChanger();
	private GameWindow window;
	private ContentView currentView;
	private ModalWindowViewer modalWindowViewer;
	
	public WindowContent(GameWindow window)
	{
		modalWindowViewer=new ModalWindowViewer(window.getWindow());
		DesignWindowBorder border=new DesignWindowBorder(window.getWindow().getTitle());
		this.setBorder(border);
		this.window=window;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		BorderMouseListener borderMouseListener=new BorderMouseListener(border,new WindowListener());
		this.addMouseListener(borderMouseListener);
		this.addMouseMotionListener(borderMouseListener);
		
	}
	
	public void createView(ContentView view)
	{
		int id=view.getViewID();
		view.setViewChangeListener(contentViewChanger);
		views.put(id, view);
	}

	public void isClosing() {
		 currentView.onClosing();
	}
	
	public void showView(int id)
	{
		if(modalWindowViewer.isOpen())
		{
			modalWindowViewer.close();
		}
		try {
			showView(views.get(id));
			this.repaint();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	

	private void showWindowView(int newViewID) {
		if(modalWindowViewer.isOpen())
		{
			modalWindowViewer.close();
		}
		ContentView view=views.get(newViewID);
		currentView=view;
		view.initView();
		modalWindowViewer.open(view);
	}
	
	public void showView(ContentView view)
	{
		currentView=view;
		view.open();
		this.removeAll();
		this.add(view.getView(),BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	private class ContentViewChanger implements ViewChangeListener{

		@Override
		public void openView( int newViewID) {
			showView(newViewID);
		}

		@Override
		public void resizeWindow(Dimension newSize) {
			if(!modalWindowViewer.isOpen())
			{
			window.updateSize(newSize);
			}
		}

		@Override
		public void openWindowView(int newViewID) {
			showWindowView(newViewID);
		}
		
	}

	
	private class WindowListener implements WindowBorderListener{

		@Override
		public void closeWindow() {
			JFrame frame=window.getWindow();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}

		@Override
		public void setLocation(int x, int y) {
			JFrame frame=window.getWindow();
			frame.setLocation(x, y);
		}
		
	}
	

	
}
