package com.starbattle.client.layout;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

	private Image background = null;
	private double angle;
	private boolean isRotating = false;
	private double x, y;

	public void setBackgroundImage(Image image) {
		background = image;
		x = 0;
		y = 0;
	}

	public void startRotating(int xr, int yr, float speed) {
		isRotating = true;
		Timer timer = new Timer();
		int update = 1000 / 30;
		timer.schedule(new Run(xr, yr, speed), 0, update);
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
				g.drawImage(background, 0, 0, null);
			}
		}
	}

	private void rotate(int xr, int yr, float speed) {
		angle += speed;
		x = Math.cos(Math.toRadians(angle)) * xr;
		y = Math.sin(Math.toRadians(angle)) * yr;
		repaint();
	}

	private class Run extends TimerTask {

		private float speed;
		private int xr, yr;

		public Run(int xr, int yr, float speed) {
			this.xr = xr;
			this.yr = yr;
			this.speed = speed;
		}

		@Override
		public void run() {
			rotate(xr, yr, speed);

		}

	}

}
