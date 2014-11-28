package com.starbattle.client.layout;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.resource.ResourceLoader;

public class DesignLabel extends JLabel {

	private static Color DEFAULT_COLOR = Color.WHITE;

	public DesignLabel(String text) {
		super(text);
		init(DEFAULT_COLOR);
	}

	public DesignLabel(String text, Icon icon) {
		super(text, icon, 0);
		init(DEFAULT_COLOR);
	}

	public DesignLabel(String text, Color color) {
		super(text);
		init(color);
	}

	public DesignLabel(String text, Icon icon, Color color) {
		super(text, icon, 0);
		init(color);
	}

	public DesignLabel(String text, Icon icon, Color color, int size) {
		super(text, icon, 0);
		init(color);
		setFontSize(size);
	}

	public DesignLabel(String text, Color color, int size) {
		super(text);
		init(color);
		setFontSize(size);
	}

	public DesignLabel(String text, int size) {
		super(text);
		init(DEFAULT_COLOR);
		setFontSize(size);
	}

	public DesignLabel(String text, String iconName) {
		super(text,ResourceLoader.loadIcon(iconName),0);		
		init(DEFAULT_COLOR);
	}
	
	public DesignLabel(String text, String iconName, Color color) {
		super(text,ResourceLoader.loadIcon(iconName),0);		
		init(color);
	}


	public DesignLabel(String text, ImageIcon icon, int size) {
		super(text, icon, 0);
		init(DEFAULT_COLOR);
		setFontSize(size);
	}

	private void init(Color color) {
		this.setOpaque(false);
		this.setFont(GUIDesign.labelFont);
		this.setForeground(color);
	}

	public void setFontSize(float size) {
		this.setFont(GUIDesign.labelFont.deriveFont(size));
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		if(this.getText()!=null)
		{
		super.paintComponent(g);
		}
	}
}
