package com.starbattle.client.window;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class ContentView {

	protected ContentPanel view=new ContentPanel();
	protected Dimension windowSize;
	private ViewChangeListener listener;
	
	
	public void open()
	{
		if(windowSize!=null)
		{
			resizeWindow(windowSize);
		}
		initView();
	}
	
	protected abstract void initView();
	
	protected abstract void onClosing();
	
	public abstract int getViewID();
	
	public void openView(int id)
	{
		listener.openView( id);
	}
	
	public void openWindowView(int id)
	{
		listener.openWindowView(id);
	}
	
	public void resizeWindow(Dimension newSize)
	{
		listener.resizeWindow(newSize);
	}
	
	public void setViewChangeListener(ViewChangeListener listener) {
		this.listener = listener;
	}
	
	public JPanel getView() {
		return view;
	}
	
	protected void clearViewPanel()
	{
		view.removeAll();
		view.revalidate();
	}
	
	public Dimension getWindowSize() {
		return windowSize;
	}
	
}
