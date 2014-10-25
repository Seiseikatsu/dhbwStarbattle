package com.starbattle.client.layout;


import java.awt.Image;

import javax.swing.JPanel;

public abstract class ViewModel {

	protected ViewModelPanel view=new ViewModelPanel();
	
	
	public void setCustomPaintPanel(CustomPaintPanelInterface customPaintPanelInterface)
	{
		view.setCustomPaintPanelInterface(customPaintPanelInterface);
	}
	
	public void setMovementCircle(int xradius, int yradius, float speed)
	{
		view.setMovementCircle(xradius, yradius, speed);
	}
	
	public void startAnimation()
	{
		startAnimation(30);
	}
	
	public void startAnimation(int fps)
	{
		view.startAnimation(fps);
	}
	
	public void setBackgroundImage(Image image, int x, int y)
	{
		view.setBackgroundImage(image, x, y);
	}
	
	public void setBackgroundImage(Image image)
	{
		setBackgroundImage(image,0,0);
	}
	
	
	public JPanel getView() {
		return view;
	}
	
}
