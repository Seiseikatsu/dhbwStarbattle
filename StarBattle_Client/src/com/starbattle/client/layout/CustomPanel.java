package com.starbattle.client.layout;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CustomPanel extends JPanel {

	private CustomPaintPanelInterface customPaintPanelInterface;
	
	public CustomPanel(CustomPaintPanelInterface customPaintPanelInterface) {
		this.customPaintPanelInterface=customPaintPanelInterface;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		customPaintPanelInterface.paint(g);
	}
}
