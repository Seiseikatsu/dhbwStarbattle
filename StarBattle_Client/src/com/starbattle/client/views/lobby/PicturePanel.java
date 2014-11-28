package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class PicturePanel extends ViewModel {

	private Image background = ResourceLoader.loadImage("space_background.jpg");
	private Image planet = ResourceLoader.loadImage("backgroundPlanet.png", 0.8f);
	private Image light = ResourceLoader.loadImage("backgroundLight.png");
	private double angle, size = 1;
	private int planetx = 350, planety = 150;

	public PicturePanel() {
		initLayout();
	}

	private void initLayout() {
		setCustomPaintPanel(new BackgroundPainter());
		startAnimation();
		view.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 3));
		view.setLayout(new BorderLayout());

		JLabel title = new DesignLabel("Star Battle Client Version 0.1", new Color(200, 200, 200), 14);
		view.add(title, BorderLayout.NORTH);
		view.add(new JLabel(ResourceLoader.loadIcon("title.png")), BorderLayout.CENTER);

	}

	private class BackgroundPainter implements CustomPaintPanelInterface {

		@Override
		public void paint(Graphics g, Dimension cSize) {
			g.drawImage(background, 0, 0, null);
			Graphics2D g2d = (Graphics2D) g;

			AffineTransform affineTransform;

			double planetW = planet.getWidth(null) * size;
			double planetH = planet.getHeight(null) * size;
			double lightW = light.getWidth(null) * size;
			double lightH = light.getHeight(null) * size;

			// Keep planet in center
			double centerx = planetx;
			double centery = planety;

			// draw Light
			double lx = centerx - lightW / 2;
			double ly = centery - lightH / 2;
			affineTransform = new AffineTransform();

			affineTransform.setToTranslation(lx, ly);
			affineTransform.rotate(angle * -0.2, lightW / 2, lightH / 2);
			affineTransform.scale(size, size);

			g2d.drawImage(light, affineTransform, null);

			// draw planet
			affineTransform = new AffineTransform();

			affineTransform.setToTranslation(centerx - planetW / 2, centery - planetH / 2);
			affineTransform.rotate(angle, planetW / 2, planetH / 2);
			affineTransform.scale(size, size);

			g2d.drawImage(planet, affineTransform, null);

			angle += 0.02;
			size = Math.cos(angle * 0.1) * 0.5;
			if (size < 0) {
				size *= -1;
			}
			size += 0.3;
		}

	}
}
