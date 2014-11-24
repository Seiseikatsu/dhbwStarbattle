package com.starbattle.client.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

import com.starbattle.client.resource.GUIDesign;

public class DesignButton extends JButton {

	private boolean isMouseOver = false;
	private static Color fontColor = new Color(255, 255, 255);
	private static Color selectedFontColor = new Color(0, 0, 0);
	private boolean designBackground = true;
	private int buttonStyle=0;
	public DesignButton(String text) {
		super(text);
		init();

	}
	
	public void setButtonStyle(int buttonStyle) {
		this.buttonStyle = buttonStyle;
		if(buttonStyle==1)
		{
			this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		}
	}

	public DesignButton(String text, Icon icon) {
		super(text, icon);
		init();
	}

	public DesignButton(Icon icon) {
		super(icon);
		designBackground = false;
		this.setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()+10));
		init();
	}

	private void init() {
		setContentAreaFilled(false);
		setBorderPainted(false);
		this.setFont(GUIDesign.buttonFont);
		this.setForeground(fontColor);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
		this.setFocusable(false);
		if(designBackground)
		{
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			
			}

			@Override
			public void mousePressed(MouseEvent e) {
		
			}

			@Override
			public void mouseExited(MouseEvent e) {
				isMouseOver = false;
				setForeground(fontColor);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				isMouseOver = true;
				setForeground(selectedFontColor);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		}
	}

	@Override
	protected void paintComponent(Graphics g) {

		if (designBackground) {
			int w = this.getWidth();
			int h = this.getHeight();

			Image img = null;
			if (isMouseOver) {
				img = GUIDesign.buttonSelected[buttonStyle].getScaledInstance(w, h, Image.SCALE_SMOOTH);
			} else {
				img = GUIDesign.button[buttonStyle].getScaledInstance(w, h, Image.SCALE_SMOOTH);
			}
			g.drawImage(img, 0, 0, null);
		}
		super.paintComponent(g);

	}

}
