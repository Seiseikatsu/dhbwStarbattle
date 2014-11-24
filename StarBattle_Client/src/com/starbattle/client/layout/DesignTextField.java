package com.starbattle.client.layout;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class DesignTextField extends JTextField {

	public DesignTextField(int width, ActionListener action) {
		super(width);
		init(action);
	}
	
	public DesignTextField( ActionListener action) {
		super();
		init(action);
	}
	
	public DesignTextField(int width, float fontSize, ActionListener action) {
		super(width);
		init(action);
		this.setFont(this.getFont().deriveFont(fontSize));
	}

	private void init(final ActionListener action) {
		this.setOpaque(false);
		this.setForeground(new Color(200, 200, 250));
		this.setCaretColor(Color.WHITE);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					action.actionPerformed(null);
				}
			}
		});
	}

}
