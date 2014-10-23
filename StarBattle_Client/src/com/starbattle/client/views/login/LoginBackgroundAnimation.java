package com.starbattle.client.views.login;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.window.CustomPaintInterface;

public class LoginBackgroundAnimation extends CustomPaintInterface {

	private Image planet = ResourceLoader.loadImage("backgroundPlanet.png");
	private Image title = ResourceLoader.loadImage("title.png");
	private int planetx = 92, planety = 124;
	private double angle;

	public LoginBackgroundAnimation() {

		Timer t = new Timer();
		t.schedule(new Repainter(), 0, 1000 / 30);
	}

	@Override
	public void paintPanel(Graphics g) {

		// draw planet
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.setToTranslation(planetx, planety);
		affineTransform.rotate(angle, planet.getWidth(null) / 2, planet.getHeight(null) / 2);
		g2d.drawImage(planet, affineTransform, null);

		// draw title
		int titlex=100;
		int titley=70;
		g.setColor(new Color(200,150,250,50));
		g.fillRect(0,titley+30,600,60);
		g.drawImage(title,titlex,titley,null);
		
		
		// draw input frame background
		g.setColor(new Color(0, 0, 0, 220));
		int b = 50;
		int x = -b;
		int y = 375;
		int w = 340;
		int h = 300;
		g.fillRoundRect(x, y, w, h, b, b);
		g.setColor(new Color(255, 255, 255, 100));
		g.drawRoundRect(x, y, w, h, b, b);

	}

	private void animate() {
		angle += Math.PI / 300;
	}

	private class Repainter extends TimerTask {

		@Override
		public void run() {
			animate();
			repaint();
		}

	}
}
