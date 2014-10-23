package com.starbattle.client.window;

import java.awt.Dimension;

public interface ViewChangeListener {

	
	public void openView(int newViewID);
	
	public void openWindowView(int newViewID);
	
	public void resizeWindow(Dimension newSize);
}
