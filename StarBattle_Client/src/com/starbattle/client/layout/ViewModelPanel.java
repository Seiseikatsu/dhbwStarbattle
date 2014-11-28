package com.starbattle.client.layout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ViewModelPanel extends JPanel {

	private Image background = null;
	private double angle;
	private double x, y;
	private int startX, startY;
	private double rotationSpeed;
	private int rotationRadiusX, rotationRadiusY;
	private boolean isRotating = false;
	private CustomPaintPanelInterface customPaintPanelInterface;
	private AnimationTimer animationTimer = new AnimationTimer();

	public void setBackgroundImage(Image image, int sx, int sy) {
		background = image;
		startX = sx;
		startY = sy;
	}

	public void setCustomPaintPanelInterface(CustomPaintPanelInterface customPaintPanelInterface) {
		this.customPaintPanelInterface = customPaintPanelInterface;
	}

	public void startAnimation(int fps) {
		animationTimer.start(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		}, fps);
	}

	public void setMovementCircle(int xr, int yr, float speed) {
		isRotating = true;
		rotationSpeed = speed;
		rotationRadiusX = xr;
		rotationRadiusY = yr;
	}

	private void update() {
		if (isRotating) {
			rotate();
		}
		repaint();
	}

	private void rotate() {
		angle += rotationSpeed;
		x = startX + Math.cos(Math.toRadians(angle)) * rotationRadiusX;
		y = startY + Math.sin(Math.toRadians(angle)) * rotationRadiusY;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			if (isRotating) {
				int w = this.getWidth() / 2;
				int h = this.getHeight() / 2;
				int xpos = (int) (w - background.getWidth(null) / 2 + x);
				int ypos = (int) (h - background.getHeight(null) / 2 + y);
				g.drawImage(background, xpos, ypos, null);
			} else {
				g.drawImage(background, startX, startY, null);
			}
		}
		if (customPaintPanelInterface != null) {
			Dimension size=new Dimension(this.getWidth(),this.getHeight());
			customPaintPanelInterface.paint(g,size);
		}
	}


}
