package com.starbattle.mapeditor.gui.components;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class TilePlacementPreview {

	private Image preview;

	public TilePlacementPreview() {

	}

	public void setPreview(Image preview) {
		this.preview = preview;
	}

	public void paint(Graphics g, int mx, int my) {
		if (preview != null) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			int w=preview.getWidth(null)/2;
			int h=preview.getHeight(null)/2;
			g2d.drawImage(preview, mx-w, my-h, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
		}
	}

}
