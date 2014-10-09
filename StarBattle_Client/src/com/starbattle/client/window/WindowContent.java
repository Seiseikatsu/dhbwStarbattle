package com.starbattle.client.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JPanel;

public class WindowContent extends JPanel{

	
	private HashMap<Integer,ContentView> views=new HashMap<Integer,ContentView>();
	private ContentViewChanger contentViewChanger=new ContentViewChanger();
	private GameWindow window;
	
	public WindowContent(GameWindow window)
	{
		this.window=window;
		this.setLayout(new BorderLayout());
	}
	
	public void createView(ContentView view)
	{
		int id=view.getViewID();
		view.setViewChangeListener(contentViewChanger);
		views.put(id, view);
	}
	
	public void showView(int id)
	{
		try {
			showView(views.get(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	
	public void showView(ContentView view)
	{
		view.initView();
		this.removeAll();
		this.add(view.getView(),BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	private class ContentViewChanger implements ViewChangeListener{

		@Override
		public void openView( int newViewID) {
			// TODO Auto-generated method stub
			showView(newViewID);
		}

		@Override
		public void resizeWindow(Dimension newSize) {
			// TODO Auto-generated method stub
			window.updateSize(newSize);
		}
		
	}
}
