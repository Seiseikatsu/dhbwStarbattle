package com.starbattle.client.window;

import java.awt.Graphics;

public abstract class CustomPaintInterface {

	
	private ContentPanel parentView;
	
	public abstract void paintPanel(Graphics g);
	
	public void setParentView(ContentPanel contentPanel) {
		this.parentView = contentPanel;
	}
	
	protected void repaint()
	{
		parentView.repaint();
	}
	
}
