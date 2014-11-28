package com.starbattle.client.window;

import java.awt.Dimension;
import java.awt.Graphics;

public abstract class CustomPaintInterface {

	
	protected ContentPanel parentView;
	
	public abstract void paintPanel(Graphics g, Dimension size);
	
	public void setParentView(ContentPanel contentPanel) {
		this.parentView = contentPanel;
	}
	
	protected void repaint()
	{
		parentView.repaint();
	}
	
}
